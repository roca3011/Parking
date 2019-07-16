package com.ceiba.Parking.databuilder;

import com.ceiba.Parking.dominio.modelo.TipoVehiculo;

public class TipoVehiculoDataBuilder {
	
	private static final long IDTIPOVEHICULO = 1;
	private static final String DESCRIPCION = "Carro";

	private long idTipoVehiculo;
	private String descripcion;
	
	public TipoVehiculoDataBuilder() {
		this.idTipoVehiculo = IDTIPOVEHICULO;
		this.descripcion = DESCRIPCION;
	};
	
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
		TipoVehiculo tiposVehiculo = new TipoVehiculo();
		tiposVehiculo.setIdTipoVehiculo(idTipoVehiculo);
		tiposVehiculo.setDescripcion(descripcion);
		
		return tiposVehiculo;
	}

}
