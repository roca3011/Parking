package com.ceiba.Parking.aplicacion;

import java.util.List;

import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.modelo.VehiculosActivos;
public interface ObtenerVehiculos {
	
	public List<Vehiculo> obtenerVehiculos();

	public List<VehiculosActivos> obtenerVehiculosActivos();
}
