package com.ceiba.Parking.dominio.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ceiba.Parking.dominio.excepcion.AccesoDenegadoException;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;

@Service
public class Parqueadero {
	
	private static final String LETRAINICIAL = "A";
	private static final String ACCESODENEGADO = "Acceso denegado";
	
	private IVehiculoRepositorio vehiculoRepositorio;
	
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {
		validarRegistro(vehiculo);
		return vehiculoRepositorio.registroVehiculo(vehiculo);
	} 
	
	public List<Vehiculo> obtenerVehiculos(){
		return vehiculoRepositorio.obtenerVehiculos();
	}
	
	private void validarRegistro(Vehiculo vehiculo) {
		validarPlaca(vehiculo.getPlaca()); 
	}
	
	private void validarPlaca(String placa) {
		String letraInicialPlaca = placa.substring(0, 1);
		
		if (letraInicialPlaca.equals(LETRAINICIAL)) {
			validarDia();
		}
	}
	
	private void validarDia() {

	  String diaActual = obtenerDiaActual();
	  
      if (diaActual.equals("Lunes")|| diaActual.equals("Domingo")) {
		throw new AccesoDenegadoException(ACCESODENEGADO);
	}
	}
	
	private String obtenerDiaActual() {
		
		String[] dias={"Domingo","Lunes","Martes", "Miercoles","Jueves","Viernes","Sabado"};
	      
		Date hoy=new Date();
	    int numeroDia=0;
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);
	    numeroDia=calendario.get(Calendar.DAY_OF_WEEK);	    
	    String diaActual = dias[numeroDia - 1];	    
	    
	    return diaActual;
	}
	
	public Parqueadero(IVehiculoRepositorio vehiculoRepositorio) {
		this.vehiculoRepositorio = vehiculoRepositorio;
	}

}
