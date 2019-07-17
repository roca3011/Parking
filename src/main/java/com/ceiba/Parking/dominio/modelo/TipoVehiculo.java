package com.ceiba.Parking.dominio.modelo;

public class TipoVehiculo {
	
	private static final String IDTIPO_VEHICULO_CAMPO_OBLIGATORIO = "El idTipoVehiculo es un campo obligatorio";
	private static final String DESCRIPCION_CAMPO_OBLIGATORIO = "Descripcion es un campo obligatorio";
	
	private long idTipoVehiculo;
	private String descripcion;
	
	public TipoVehiculo() {
	}

	public TipoVehiculo(long idTipoVehiculo, String descripcion) {
		ValidadorArgumento.validadorCampoObligatorio(idTipoVehiculo, IDTIPO_VEHICULO_CAMPO_OBLIGATORIO);
		ValidadorArgumento.validadorCampoObligatorio(idTipoVehiculo, DESCRIPCION_CAMPO_OBLIGATORIO);
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
