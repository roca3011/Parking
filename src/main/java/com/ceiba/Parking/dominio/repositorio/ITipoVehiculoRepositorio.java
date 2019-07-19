package com.ceiba.parking.dominio.repositorio;

import java.util.List;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;

public interface ITipoVehiculoRepositorio {
	
	public List<TipoVehiculo> obtenerTiposDeVehiculo();
	
	public TipoVehiculo obtenerTipoVehiculoPorDesc(String descripcion);

}
