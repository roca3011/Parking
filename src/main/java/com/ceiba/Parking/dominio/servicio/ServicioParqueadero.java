package com.ceiba.Parking.dominio.servicio;

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
import com.ceiba.Parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.Parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivos;

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
			validarTipoVehiculo(vehiculo.getTipoVehiculo().getDescripcion());
			vehiculo.setFechaCreacion(obtenerfechaAtual());
			vehiculoEntidad = vehiculoRepositorio.registroVehiculo(vehiculo);
		}		
		registrarFactura(vehiculoEntidad);
		
		return vehiculoEntidad;		
	} 
	
	public List<Factura> obtenerFacturasVehiculo(Vehiculo vehiculo){
		return facturaRepositorio.obtenerFacturasPorVehiculo(vehiculo);
	}
	
	//nombramiento
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
		//validarTipoVehiculo(vehiculo.getTipoVehiculo().getDescripcion());		
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
	
	public void validarTipoVehiculo(String descripcion) {
		
		TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(descripcion);		
		ValidadorArgumento.validadorCampoObligatorio(tipoVehiculo, DATOSINCORRECTOS);

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
