package com.ceiba.parking.aplicacion;

import java.util.List;

import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;
public interface ObtenerVehiculos {
	
	public List<Vehiculo> obtenerVehiculos();

	public List<VehiculosActivos> obtenerVehiculosActivos();
}
