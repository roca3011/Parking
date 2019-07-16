package com.ceiba.Parking.dominio.modelo;

import lombok.Getter;
import lombok.Setter;

//@Setter
//@Getter
public class TipoVehiculo {
	
	private long idTipoVehiculo;
	private String descripcion;
	
	public TipoVehiculo() {
	}

	public TipoVehiculo(long idTipoVehiculo, String descripcion) {
		this.idTipoVehiculo = idTipoVehiculo;
		this.descripcion = descripcion;
	}
	
	public long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}
	public void setIdTipoVehiculo(long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
