package com.ceiba.parking.infraestructura.persistencia.mapper;

import com.ceiba.parking.dominio.modelo.VehiculosActivos;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculosActivosEntidad;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculosActivosProyeccion;

public class VehiculosActivosMapper {
	
	private VehiculosActivosMapper() {
	}
	
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
