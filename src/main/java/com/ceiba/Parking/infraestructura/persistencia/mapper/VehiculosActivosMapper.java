package com.ceiba.Parking.infraestructura.persistencia.mapper;

import com.ceiba.Parking.dominio.modelo.VehiculosActivos;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivosEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivosProyeccion;

public class VehiculosActivosMapper {
	
	public static VehiculosActivos convertirADominio(VehiculosActivosEntidad vehiculosActivosEntidad) {
		VehiculosActivos vehiculosActivos = new VehiculosActivos();
		
		vehiculosActivos.setPlaca(vehiculosActivosEntidad.getplaca());
		vehiculosActivos.setFechaIngreso(vehiculosActivosEntidad.getfechaIngreso());
		vehiculosActivos.setDescripcion(vehiculosActivosEntidad.getdescripcion());
		
		return vehiculosActivos;
		
	}
	
	public static VehiculosActivosEntidad convertirAEntidad(VehiculosActivos vehiculosActivos) {
		VehiculosActivosEntidad vehiculosActivosEntidad = new VehiculosActivosEntidad();
		
		vehiculosActivosEntidad.setPlaca(vehiculosActivos.getPlaca());
		vehiculosActivosEntidad.setFechaIngreso(vehiculosActivos.getFechaIngreso());
		vehiculosActivosEntidad.setDescripcion(vehiculosActivos.getDescripcion());
		
		return vehiculosActivosEntidad;
		
	}
	
	public static VehiculosActivos convertirProyeccionaEntidad(VehiculosActivosProyeccion vehiculosActivosProyeccion) {
		VehiculosActivos vehiculosActivos = new VehiculosActivos();
		
		vehiculosActivos.setPlaca(vehiculosActivosProyeccion.getplaca());
		vehiculosActivos.setFechaIngreso(vehiculosActivosProyeccion.getfechaIngreso());
		vehiculosActivos.setDescripcion(vehiculosActivosProyeccion.getdescripcion());
		
		return vehiculosActivos;
		
	}

}
