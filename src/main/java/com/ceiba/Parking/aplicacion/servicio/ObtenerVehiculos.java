package com.ceiba.Parking.aplicacion.servicio;

import java.util.List;

import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivos;

public interface ObtenerVehiculos {
	
	public List<Vehiculo> obtenerVehiculos();

	public List<VehiculosActivos> ObtenerVehiculosActivos();
}
