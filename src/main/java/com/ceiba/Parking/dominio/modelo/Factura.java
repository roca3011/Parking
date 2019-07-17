package com.ceiba.Parking.dominio.modelo;

import java.util.Date;

public class Factura {
	
	private static final String IDFACTURA_CAMPO_OBLIGATORIO = "El idFactura es un campo obligatorio";
	private static final String FECHA_INGRESO_CAMPO_OBLIGATORIO = "La fecha de ingreso es un campo obligatorio";
	
	private int idFactura;
	private Date fechaIngreso;
	private Date fechaSalida;
	private float valorTotal;
	private Vehiculo vehiculo;
	private boolean estado;
	
	public Factura() {
	}

	public Factura(int idFactura, Date fechaIngreso, Date fechaSalida, float valorTotal, Vehiculo vehiculo, boolean estado) {
		ValidadorArgumento.validadorCampoObligatorio(idFactura, IDFACTURA_CAMPO_OBLIGATORIO);
		ValidadorArgumento.validadorCampoObligatorio(idFactura, FECHA_INGRESO_CAMPO_OBLIGATORIO);
		this.idFactura = idFactura;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorTotal = valorTotal;
		this.vehiculo = vehiculo;
		this.estado = estado;
	}
	
	public Factura(Date fechaIngreso, float valorTotal, Vehiculo vehiculo) {
		this.fechaIngreso = fechaIngreso;
		this.valorTotal = valorTotal;
		this.vehiculo = vehiculo;
		this.estado = false;
	}
	
	public int getIdFactura() {
		return idFactura;
	}	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
