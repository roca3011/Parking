package com.ceiba.Parking.dominio.repositorio;

import java.util.List;

import com.ceiba.Parking.dominio.modelo.TipoVehiculo;

public interface ITipoVehiculoRepositorio {
	
	public List<TipoVehiculo> obtenerTiposDeVehiculo();
	
	public TipoVehiculo obtenerTipoVehiculoPorDesc(String descripcion);

}
