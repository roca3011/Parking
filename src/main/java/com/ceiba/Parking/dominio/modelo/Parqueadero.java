package com.ceiba.Parking.dominio.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.Parking.dominio.excepcion.AccesoDenegadoException;
import com.ceiba.Parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.Parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.excepcion.DatosIncorrectos;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivos;

@Service
public class Parqueadero {
	
	private static final int	LIMITECUPOSCARROS = 20;
	private static final int	LIMITECUPOSMOTOS = 10;
	private static final String LETRAINICIAL = "A";
	private static final String ACCESODENEGADOPORMATRICULA = "No está en un dia hábil";
	private static final String CUPOSVEHICULO = "No hay mas cupos para el tipo de vehiculo";
	private static final String DATOSINCORRECTOS = "Datos incorrectos";
	private static final String LUNES = "Lunes";
	private static final String	MARTES = "Martes";
	private static final String	MIERCOLES = "Miercoles";
	private static final String	JUEVES = "Jueves";
	private static final String	VIERNES = "Viernes";
	private static final String	SABADO = "Sabado";
	private static final String DOMINGO = "Domingo";
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";	
	
	@Autowired
	private IVehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ITipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	@Autowired
	private IFacturaRepositorio facturaRepositorio;
	
	public Parqueadero() {
	}
	
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {
		validarRegistro(vehiculo);
		Vehiculo vehiculodata = vehiculoRepositorio.registroVehiculo(vehiculo);
		registrarFactura(vehiculodata);
		
		return vehiculodata;		
	} 
	
	public List<VehiculosActivos> ObtenerVehiculosActivos(){
		return vehiculoRepositorio.vehiculosParqueadero();
	}
	
	public void registrarFactura(Vehiculo vehiculo) {
		Factura factura = new Factura();
		
		Date hoy=new Date();
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);
	    Date fechaIngreso = calendario.getTime();
	    
		factura.setVehiculo(vehiculo);
		factura.setFechaIngreso(fechaIngreso);
		factura.setEstado(true);
		facturaRepositorio.registrarEntrada(factura);
		
	}
	
	public List<Vehiculo> obtenerVehiculos(){
		return vehiculoRepositorio.obtenerVehiculos();
	}
	
	public void validarRegistro(Vehiculo vehiculo) {
		validarCupo(vehiculo.getTipoVehiculo());
		validarTipoVehiculo(vehiculo.getTipoVehiculo().getDescripcion());		
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
				throw new AccesoDenegadoException(DATOSINCORRECTOS);
		}

	}
	
	public void validarCupo(int cantidadVehiculosPorTipo, int limiteCupos) {
		if (cantidadVehiculosPorTipo > limiteCupos) {
			throw new AccesoDenegadoException(CUPOSVEHICULO);
		}
	}
	
	public void validarTipoVehiculo(String descripcion) {
		
		TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(descripcion);		
		
		if (tipoVehiculo == null) {
			throw new DatosIncorrectos(DATOSINCORRECTOS);
		}
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
		throw new AccesoDenegadoException(ACCESODENEGADOPORMATRICULA);
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
	
	public Parqueadero(IVehiculoRepositorio vehiculoRepositorio, ITipoVehiculoRepositorio tipoVehiculoRepositorio) {
		this.vehiculoRepositorio = vehiculoRepositorio;
		this.tipoVehiculoRepositorio = tipoVehiculoRepositorio;
	}

}
