package com.ceiba.parking.infraestructura.persistencia.entidad;

public interface VehiculosActivosProyeccion {
	
	 String getplaca();
	 
	 String getfechaIngreso();
	 
	 String getdescripcion();
	 
	 VehiculosActivosEntidad obtenerVehiculosActivos();

}
