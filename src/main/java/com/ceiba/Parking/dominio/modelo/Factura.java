package com.ceiba.Parking.dominio.modelo;

import java.util.Date;

public class Factura {
	
	private int idFactura;
	private Date fechaIngreso;
	private Date fechaSalida;
	private float valorTotal;
	private Vehiculo vehiculo;
	private boolean estado;
	
	public Factura() {
	}

	public Factura(int idFactura, Date fechaIngreso, Date fechaSalida, float valorTotal, Vehiculo vehiculo, boolean estado) {
		super();
		this.idFactura = idFactura;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorTotal = valorTotal;
		this.vehiculo = vehiculo;
		this.estado = estado;
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
