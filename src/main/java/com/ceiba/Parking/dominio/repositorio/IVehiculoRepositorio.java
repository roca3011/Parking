package com.ceiba.Parking.dominio.repositorio;

import java.util.List;

import com.ceiba.Parking.dominio.modelo.Vehiculo;

public interface IVehiculoRepositorio {
	
	public Vehiculo registroVehiculo(Vehiculo vehiculo);
	
	public List<Vehiculo> obtenerVehiculos();

}
