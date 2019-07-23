package com.ceiba.parking.dominio.repositorio;

import java.util.List;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;

public interface IVehiculoRepositorio {
	
	/**Registra un nuevo vehiculo*/
	public Vehiculo registroVehiculo(Vehiculo vehiculo);
	
	/**Obtiene la cantidad de vehiculos dado su tipo ej: Carro*/
	public int cantidadVehiculos(TipoVehiculo tipoVehiculo);
	
	/**Obteine la lista de vehiculos que estan en el parqueadero*/
	public List<VehiculosActivos> vehiculosParqueadero();
	
	/**Obtiene el vehiculo dada su placa*/
	Vehiculo obtenerVehiculoPorplaca(String placa);
}
