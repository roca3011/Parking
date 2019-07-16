package com.ceiba.Parking.dominio.modelo;

import lombok.Getter;
import lombok.Setter;

//@Setter
//@Getter
public class Vehiculo {
	
	private long idVehiculo;
	private TipoVehiculo tipoVehiculo;
	private int cilindraje;
	private String placa;
	private boolean activo;
	
	public Vehiculo() {
	}
	
	public Vehiculo(long idVehiculo, TipoVehiculo tipoVehiculo, int cilindraje, String placa, boolean activo) {
		super();
		this.idVehiculo = idVehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.placa = placa;
		this.activo = activo;
	}
	
	public long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	} 

}
