package com.ceiba.parking.dominio.repositorio;

import java.util.List;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;

public interface ITipoVehiculoRepositorio {
	
	/** Obtiene los tipos de vehiculo ej:1,Moto*/
	public List<TipoVehiculo> obtenerTiposDeVehiculo();
	
	/** Obtiene los tipos de vehiculo dada su descripcion ej: Moto*/
	public TipoVehiculo obtenerTipoVehiculoPorDesc(String descripcion);

}
