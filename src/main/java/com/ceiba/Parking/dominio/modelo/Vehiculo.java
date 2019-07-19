package com.ceiba.parking.dominio.modelo;

import java.util.Date;

public class Vehiculo {
	
	private static final String IDVEHICULO_CAMPO_OBLIGATORIO = "El campo idVehiculo es obligatorio";
	private static final String TIPOVEHICULO_CAMPO_OBLIGATORIO = "El campo tipoVehiculo es obligatorio";
	private static final String CILINDRAJE_CAMPO_OBLIGATORIO = "El campo cilindraje es obligatorio";
	private static final String PLACA_CAMPO_OBLIGATORIO = "El campo placa es obligatorio";
	
	private long idVehiculo;
	private TipoVehiculo tipoVehiculo;
	private int cilindraje;
	private String placa;
	private Date fechaCreacion;
	
	public Vehiculo() {
	}
	
	public Vehiculo(long idVehiculo, TipoVehiculo tipoVehiculo, int cilindraje, String placa, Date fechaCreacion) {
		ValidadorArgumento.validadorCampoObligatorio(idVehiculo, IDVEHICULO_CAMPO_OBLIGATORIO);
		ValidadorArgumento.validadorCampoObligatorio(tipoVehiculo, TIPOVEHICULO_CAMPO_OBLIGATORIO);
		ValidadorArgumento.validadorCampoObligatorio(cilindraje, CILINDRAJE_CAMPO_OBLIGATORIO);
		ValidadorArgumento.validadorCampoObligatorio(placa, PLACA_CAMPO_OBLIGATORIO);
		this.idVehiculo = idVehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.placa = placa;
		this.fechaCreacion = fechaCreacion;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
