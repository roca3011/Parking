package com.ceiba.Parking.infraestructura.persistencia.entidad;

public interface VehiculosActivosProyeccion {
	
	 String getplaca();
	 
	 String getfechaIngreso();
	 
	 String getdescripcion();
	 
	 VehiculosActivosEntidad obtenerVehiculosActivos();

}
