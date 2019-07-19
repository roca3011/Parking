package com.ceiba.parking.dominio.repositorio;

import java.util.List;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;

public interface IVehiculoRepositorio {
	
	public Vehiculo registroVehiculo(Vehiculo vehiculo);
	
	public List<Vehiculo> obtenerVehiculos();
	
	public int cantidadVehiculos(TipoVehiculo tipoVehiculo);
	
	public List<VehiculosActivos> vehiculosParqueadero();
	
	Vehiculo obtenerVehiculoPorplaca(String placa);
}
