package com.ceiba.parking.databuilder;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;

public class TipoVehiculoDataBuilder {
	
	private static final long ID_TIPOVEHICULO = 1;
	private static final String CARRO = "Carro";

	private long idTipoVehiculo;
	private String descripcion;
	
	public TipoVehiculoDataBuilder() {
		this.idTipoVehiculo = ID_TIPOVEHICULO;
		this.descripcion = CARRO;
	}
	
	public TipoVehiculoDataBuilder(long idTipoVehiculo, String descripcion) {
		this.idTipoVehiculo = idTipoVehiculo;
		this.descripcion = descripcion;
	}

	public TipoVehiculoDataBuilder obtenerTipoDeVehiculoPorId(long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
		return this;
	}
	
	public TipoVehiculoDataBuilder obtenerTipoDeVehiculo(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
	
	public TipoVehiculo build() {
		TipoVehiculo tiposVehiculo = new TipoVehiculo(this.idTipoVehiculo,this. descripcion);
		return tiposVehiculo;
	}

}
