package com.ceiba.parking.dominio.servicio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ceiba.parking.dominio.excepcion.AccesoDenegado;
import com.ceiba.parking.dominio.excepcion.DatosIncorrectos;
import com.ceiba.parking.dominio.excepcion.ExcepcionFactura;
import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.ValidadorArgumento;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;
import com.ceiba.parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.parking.dominio.repositorio.IVehiculoRepositorio;

public class ServicioParqueadero {
	
	public static final int	LIMITECUPOSCARROS = 20;
	public static final int	LIMITECUPOSMOTOS = 10;
	public static final String LETRAINICIAL = "A";
	private static final String ACCESODENEGADOPORMATRICULA = "No está en un dia hábil";
	public static final String CUPOSVEHICULO = "No hay mas cupos para el tipo de vehiculo";
	public static final String DATOSINCORRECTOS = "Datos incorrectos";
	private static final String VEHICULOACTIVO = "El vehiculo ya se encuentra en el parqueadero";
	private static final String FACTURANOENCONTRADA = "El vehiculo ingresado no tiene factura";
	private static final String VEHICULONOREGISTRADO = "El vehiculo ingresado no se encuentra registrado";
	private static final String LUNES = "Lunes";
	private static final String	MARTES = "Martes";
	private static final String	MIERCOLES = "Miercoles";
	private static final String	JUEVES = "Jueves";
	private static final String	VIERNES = "Viernes";
	private static final String	SABADO = "Sabado";
	private static final String DOMINGO = "Domingo";
	public static final String CARRO = "CARRO";
	public static final String MOTO = "MOTO";	
	private static final float VALORINICIAL = 0.0f;
	private static final int CILINDRAJEMOTO = 500;
	private static final float VALORDIACARRO = 8000.0f;
	private static final float VALORDIAMOTO = 4000.0f;
	private static final float VALORHORACARRO = 1000.0f;
	private static final float VALORHORAMOTO = 500.0f;
	private static final float VALORADICIONALMOTO = 2000.0f;
	private static final int HORASPARACOBRARDIA = 9;
	private static final int HORASDIA = 24;
	
	private IVehiculoRepositorio vehiculoRepositorio;
	private ITipoVehiculoRepositorio tipoVehiculoRepositorio;
	private IFacturaRepositorio facturaRepositorio;
	
	public ServicioParqueadero() {}
	
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
		if (vehiculoRegistrado == null) {
			throw new DatosIncorrectos(VEHICULONOREGISTRADO);
		}
		factura = facturaRepositorio.registrarSalida(vehiculoRegistrado);
		if (factura == null) {
			throw new ExcepcionFactura(FACTURANOENCONTRADA);
		}
		
		Date fechaSalida = obtenerfechaAtual();
		float valorTotal = calcularValorAPagar(vehiculoRegistrado, factura, fechaSalida);
		factura = actualizarFactura(factura, fechaSalida, valorTotal);
		actualizarFacturaEntidad(factura);
		
		return factura;
	}
	
	public float calcularValorAPagar(Vehiculo vehiculoRegistrado, Factura factura, Date fechaSalida) {
		
		int horas = calcularTiempoDeParqueo(factura.getFechaIngreso(), fechaSalida);		
		return calcularValorTotal(vehiculoRegistrado, horas);
	}
	
	public int calcularTiempoDeParqueo(Date fechaIngreso, Date fechaSalida) {
		
		int dias=0;
        int horas=0;
        int tiempoHoras = 0;  
        
        final long miliSegundosPorMinuto = 60000;
        final long miliSegundosPorHora = 3600000;
		final long miliSegundosPorDia = miliSegundosPorHora * HORASDIA;
        
        Calendar calendarFechaIngreso= Calendar.getInstance();
        calendarFechaIngreso.setTime(fechaIngreso);
        Calendar calendarFechaSalida= Calendar.getInstance();
        calendarFechaSalida.setTime(fechaSalida);
        
        long diferenciaFechas = calendarFechaSalida.getTimeInMillis() - calendarFechaIngreso.getTimeInMillis();
        
		dias = (int) (diferenciaFechas / miliSegundosPorDia);
		diferenciaFechas = diferenciaFechas - (dias * miliSegundosPorDia);
		horas = (int) (diferenciaFechas / miliSegundosPorHora);
		diferenciaFechas = diferenciaFechas - (horas * miliSegundosPorHora);
		int minutos = (int) (diferenciaFechas / miliSegundosPorMinuto);
		
		if (minutos > 0 && horas < HORASPARACOBRARDIA) {
			horas+= 1;
		}

		tiempoHoras = (dias* HORASDIA) + horas;
        return tiempoHoras;
		
	}
		
	public float calcularValorTotal(Vehiculo vehiculoRegistrado, int horas) {
		float valorTotal = 0.0F;
		String tipoVehiculo = vehiculoRegistrado.getTipoVehiculo().getDescripcion();
		
		if (tipoVehiculo.equalsIgnoreCase(MOTO)) {			
			if (horas >= HORASPARACOBRARDIA) {
				valorTotal = calcularTotalConAdicionales(horas, VALORDIAMOTO, VALORHORAMOTO);
			}else {
				valorTotal += horas * VALORHORAMOTO;
			}
			if (vehiculoRegistrado.getCilindraje() > CILINDRAJEMOTO && horas > 0) {
				valorTotal += VALORADICIONALMOTO;
			}
		}
		
		if (tipoVehiculo.equalsIgnoreCase(CARRO)) {
			if (horas >= HORASPARACOBRARDIA) {
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
		
		/** Se cobra dia por horas iguales o mayores a HORASPARACOBRARDIA y menores a 24 horas*/
		if (horas < HORASDIA) {
			dias += 1;
			
		}else {
			dias = (horas/24);
			horasAdicionales = horas - (dias * 24);
		}			
		
		float valorDias = dias * valorDia;
		float valorHorasAdicionales = horasAdicionales  * valorHora;
		return valorDias + valorHorasAdicionales;
	}
	
	public Factura actualizarFactura(Factura factura, Date fechaSalida, float valorTotal) {
		
		factura.setFechaSalida(fechaSalida);
		factura.setValorTotal(valorTotal);
		factura.setEstado(false);
		
		return factura;
	}
	
	public Factura actualizarFacturaEntidad(Factura factura) {		 
		return facturaRepositorio.actualizarEntidadFactura(factura);
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
		
	public Vehiculo validarExistenciaPorPlaca(String placa) {
		return  vehiculoRepositorio.obtenerVehiculoPorplaca(placa);		
	}
	
	public void validarRegistro(Vehiculo vehiculo) {
		validarCupo(vehiculo.getTipoVehiculo());		
		validarPlaca(vehiculo.getPlaca()); 
	}
	
	public void validarCupo(TipoVehiculo tipoVehiculo) {
		int cantidadVehiculosPorTipo = vehiculoRepositorio.cantidadVehiculos(tipoVehiculo);		
		switch (tipoVehiculo.getDescripcion().toUpperCase()) {
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
	
	public void validarPlaca(String placa) {
		String letraInicialPlaca = placa.substring(0, 1);
		
		if (letraInicialPlaca.equals(LETRAINICIAL)) {
			validarDia();
		}
	}
	
	public void validarDia() {

	  String diaActual = obtenerDiaActual();
	  
      if (diaActual.equalsIgnoreCase(LUNES)|| diaActual.equalsIgnoreCase(DOMINGO)) {
		throw new AccesoDenegado(ACCESODENEGADOPORMATRICULA);
      }
	}
	
	public String obtenerDiaActual() {
		
		String[] dias={DOMINGO,LUNES,MARTES, MIERCOLES,JUEVES,VIERNES,SABADO};
	      
		Date hoy=new Date();
	    int numeroDia=0;
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);
	    numeroDia=calendario.get(Calendar.DAY_OF_WEEK);	    
	    
	    return dias[numeroDia - 1];
	}
	
	public Date obtenerfechaAtual() {		
				
		Date hoy=new Date();
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);	    
	    return calendario.getTime();
	}

	public List<TipoVehiculo> obtenerTiposVehiculo() {		
		return tipoVehiculoRepositorio.obtenerTiposDeVehiculo();
	}
	
	public Factura obtenerFactura(String placa) {
		Vehiculo vehiculoRegistrado;
		Factura factura;
		vehiculoRegistrado = validarExistenciaPorPlaca(placa);
		if (vehiculoRegistrado == null) {
			throw new DatosIncorrectos(VEHICULONOREGISTRADO);
		}
		factura = facturaRepositorio.registrarSalida(vehiculoRegistrado);
		if (factura == null) {
			throw new ExcepcionFactura(FACTURANOENCONTRADA);
		}
		
		Date fechaSalida = obtenerfechaAtual();
		float valorTotal = calcularValorAPagar(vehiculoRegistrado, factura, fechaSalida);
		factura = actualizarFactura(factura, fechaSalida, valorTotal);
		actualizarFacturaEntidad(factura);
		
		return factura;
	}
}
