package com.ceiba.Parking.dominio.repositorio;

import java.util.List;

import com.ceiba.Parking.dominio.modelo.TipoVehiculo;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivos;

public interface IVehiculoRepositorio {
	
	public Vehiculo registroVehiculo(Vehiculo vehiculo);
	
	public List<Vehiculo> obtenerVehiculos();
	
	public int cantidadVehiculos(TipoVehiculo tipoVehiculo);
	
	public List<VehiculosActivos> vehiculosParqueadero();
	
	Vehiculo obtenerVehiculoPorplaca(String placa);
}
