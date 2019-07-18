package com.ceiba.Parking.dominio.servicio;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.ceiba.Parking.dominio.excepcion.AccesoDenegado;
import com.ceiba.Parking.dominio.excepcion.DatosIncorrectos;
import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.TipoVehiculo;
import com.ceiba.Parking.dominio.modelo.ValidadorArgumento;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.modelo.VehiculosActivos;
import com.ceiba.Parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.Parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivosEntidad;

public class ServicioParqueadero {
	
	private static final int	LIMITECUPOSCARROS = 20;
	private static final int	LIMITECUPOSMOTOS = 10;
	private static final String LETRAINICIAL = "A";
	private static final String ACCESODENEGADOPORMATRICULA = "No está en un dia hábil";
	private static final String CUPOSVEHICULO = "No hay mas cupos para el tipo de vehiculo";
	private static final String DATOSINCORRECTOS = "Datos incorrectos";
	private static final String VEHICULOACTIVO = "El vehiculo ya se encuentra en el parqueadero";
	private static final String LUNES = "Lunes";
	private static final String	MARTES = "Martes";
	private static final String	MIERCOLES = "Miercoles";
	private static final String	JUEVES = "Jueves";
	private static final String	VIERNES = "Viernes";
	private static final String	SABADO = "Sabado";
	private static final String DOMINGO = "Domingo";
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";	
	private static final float VALORINICIAL = 0.0f;
	private static final int CILINDRAJEMOTO = 500;
	private static final float VALORDIACARRO = 8000.0f;
	private static final float VALORDIAMOTO = 4000.0f;
	private static final float VALORHORACARRO = 1000.0f;
	private static final float VALORHORAMOTO = 500.0f;
	private static final float VALORADICIONALMOTO = 2000.0f;
	
	private IVehiculoRepositorio vehiculoRepositorio;
	private ITipoVehiculoRepositorio tipoVehiculoRepositorio;
	private IFacturaRepositorio facturaRepositorio;
	
	public ServicioParqueadero() {
	}
	
	public ServicioParqueadero(IVehiculoRepositorio vehiculoRepositorio, ITipoVehiculoRepositorio tipoVehiculoRepositorio, IFacturaRepositorio facturaRepositorio) {
		this.vehiculoRepositorio = vehiculoRepositorio;
		this.tipoVehiculoRepositorio = tipoVehiculoRepositorio;		
		this.facturaRepositorio = facturaRepositorio;
	}
	
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {
		Vehiculo vehiculoEntidad;
		
		validarRegistro(vehiculo);
		vehiculoEntidad = validarExistenciaPorPlaca(vehiculo.getPlaca());
		
		if (vehiculoEntidad != null) {
			validarVehiculoActivo(vehiculoEntidad);
		}else {
			TipoVehiculo tipoVehiculo = validarTipoVehiculo(vehiculo.getTipoVehiculo().getDescripcion());
			vehiculo.setFechaCreacion(obtenerfechaAtual());
			vehiculo.setTipoVehiculo(tipoVehiculo);
			vehiculoEntidad = vehiculoRepositorio.registroVehiculo(vehiculo);
		}		
		registrarFactura(vehiculoEntidad);
		
		return vehiculoEntidad;		
	} 
	
	public List<Factura> obtenerFacturasVehiculo(Vehiculo vehiculo){
		return facturaRepositorio.obtenerFacturasPorVehiculo(vehiculo);
	}
	
	public Factura obtenerFacturaVehiculo(Vehiculo vehiculo){
		Vehiculo vehiculoRegistrado;
		Factura factura;
		vehiculoRegistrado = validarExistenciaPorPlaca(vehiculo.getPlaca());
		factura = facturaRepositorio.registrarSalida(vehiculoRegistrado);
		factura = calcularValorAPagar(vehiculoRegistrado, factura);
		
		return factura;
	}
	
	public Factura calcularValorAPagar(Vehiculo vehiculoRegistrado, Factura factura) {
		Date fechaSalida = obtenerfechaAtual();
		int horas = calcularTiempoDeParqueo(factura.getFechaIngreso(), fechaSalida);
		float valorTotal = calcularValorTotal(vehiculoRegistrado, horas);
		factura.setFechaSalida(fechaSalida);
		factura.setValorTotal(valorTotal);
		factura.setEstado(false);
		
		return factura;
	}
	
	public int calcularTiempoDeParqueo(Date fechaIngreso, Date fechaSalida) {
		
		int dias=0;
        int horas=0;
        int tiempo = 0;  
        
        final long miliSegundosPorMinuto = 60000;
        final long miliSegundosPorHora = 3600000;
		final long miliSegundosPorDia = miliSegundosPorHora * 24;
        
        Calendar calendario1= Calendar.getInstance();
        calendario1.setTime(fechaIngreso);
        Calendar calendario2= Calendar.getInstance();
        calendario2.setTime(fechaSalida);
        
        long diferencia = calendario2.getTimeInMillis() - calendario1.getTimeInMillis();
		dias = (int) (diferencia / miliSegundosPorDia);
		diferencia = diferencia - (dias * miliSegundosPorDia);
		horas = (int) (diferencia / miliSegundosPorHora);
		diferencia = diferencia - (horas * miliSegundosPorHora);
		int minutos = (int) (diferencia / miliSegundosPorMinuto);
		
		if (minutos > 0) {
			horas+= 1;
		}

        tiempo = (dias*24) + horas+8;
        return tiempo;
		
	};
		
	public float calcularValorTotal(Vehiculo vehiculoRegistrado, int horas) {
		float valorTotal = 0.0F;
		String tipoVehiculo = vehiculoRegistrado.getTipoVehiculo().getDescripcion();
		
		if (tipoVehiculo.equalsIgnoreCase(MOTO)) {			
			if (horas >= 9) {
				valorTotal = calcularTotalConAdicionales(horas, VALORDIAMOTO, VALORHORAMOTO);
			}else {
				valorTotal += horas * VALORHORAMOTO;
			}
			if (vehiculoRegistrado.getCilindraje() > CILINDRAJEMOTO) {
				valorTotal += 2000;
			}
		}
		
		if (tipoVehiculo.equalsIgnoreCase(CARRO)) {
			if (horas >= 9) {
				valorTotal = calcularTotalConAdicionales(horas, VALORDIACARRO, VALORHORACARRO);
			}else {
				valorTotal += horas * VALORHORACARRO;
			}
		}		 		
		return valorTotal;
	}
	
	public float calcularTotalConAdicionales(int horas, float valorDia, float valorHora) {
		
		int dias = 0;
		int horasAdicionales = 0;
		
		/** Se cobra dia por horas iguales o mayores a 9*/
		if (horas < 24) {
			dias += 1;
			
		}else {
			dias = (horas/24);
			horasAdicionales = horas - (dias * 24);
		}
		
		//dias = (horas/24);
		//horas -= dias * 24;
		//int horasAdicionales = horas - (dias * 24);				
		
		float valorDias = dias * valorDia;
		float valorHorasAdicionales = horasAdicionales  * valorHora;
		return valorDias + valorHorasAdicionales;
	}
	
	public List<VehiculosActivos> obtenerVehiculosActivos(){
		return vehiculoRepositorio.vehiculosParqueadero();
	}
	
	public void registrarFactura(Vehiculo vehiculo) {
		Factura factura;
	    Date fechaIngreso = obtenerfechaAtual();	    
	    factura = new Factura(fechaIngreso, VALORINICIAL, vehiculo);
		facturaRepositorio.registrarEntrada(factura);
		
	}
	
	public void validarVehiculoActivo(Vehiculo vehiculo) {		
		Factura factura = null;
		factura = facturaRepositorio.obtenerFacturaActiva(vehiculo);			
		if (factura != null) {
			throw new AccesoDenegado(VEHICULOACTIVO);
		}
	}
	
	public List<Vehiculo> obtenerVehiculos(){
		return vehiculoRepositorio.obtenerVehiculos();
	}
	
	public Vehiculo validarExistenciaPorPlaca(String placa) {
		return  vehiculoRepositorio.obtenerVehiculoPorplaca(placa);		
	}
	
	public void validarRegistro(Vehiculo vehiculo) {
		validarCupo(vehiculo.getTipoVehiculo());		
		validarPlaca(vehiculo.getPlaca()); 
	}
	
	public void validarCupo(TipoVehiculo tipoVehiculo) {
		int cantidadVehiculosPorTipo = vehiculoRepositorio.cantidadVehiculos(tipoVehiculo);
				
		switch (tipoVehiculo.getDescripcion()) {
			case MOTO:
				validarCupo(cantidadVehiculosPorTipo, LIMITECUPOSMOTOS);
				break;
			case CARRO:
				validarCupo(cantidadVehiculosPorTipo, LIMITECUPOSCARROS);
				break;
			default:
				throw new AccesoDenegado(DATOSINCORRECTOS);
		}
	}
	
	public void validarCupo(int cantidadVehiculosPorTipo, int limiteCupos) {
		if (cantidadVehiculosPorTipo > limiteCupos) {
			throw new AccesoDenegado(CUPOSVEHICULO);
		}
	}
	
	public TipoVehiculo validarTipoVehiculo(String descripcion) {
		
		TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(descripcion);		
		ValidadorArgumento.validadorCampoObligatorio(tipoVehiculo, DATOSINCORRECTOS);
		return tipoVehiculo;

	}
	
	private void validarPlaca(String placa) {
		String letraInicialPlaca = placa.substring(0, 1);
		
		if (letraInicialPlaca.equals(LETRAINICIAL)) {
			validarDia();
		}
	}
	
	private void validarDia() {

	  String diaActual = obtenerDiaActual();
	  
      if (diaActual.equals(LUNES)|| diaActual.equals(DOMINGO)) {
		throw new AccesoDenegado(ACCESODENEGADOPORMATRICULA);
      }
	}
	
	private String obtenerDiaActual() {
		
		String[] dias={DOMINGO,LUNES,MARTES, MIERCOLES,JUEVES,VIERNES,SABADO};
	      
		Date hoy=new Date();
	    int numeroDia=0;
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);
	    numeroDia=calendario.get(Calendar.DAY_OF_WEEK);	    
	    String diaActual = dias[numeroDia - 1];	    
	    
	    return diaActual;
	}
	
	public Date obtenerfechaAtual() {		
				
		Date hoy=new Date();
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);	    
	    return calendario.getTime();
	}
}
