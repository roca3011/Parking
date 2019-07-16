package com.ceiba.Parking.infraestructura.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "factura")
public class FacturaEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private int idFactura;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaIngreso", nullable = false)
	private Date fechaIngreso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaSalida", nullable = true)
	private Date fechaSalida;
	
	@Column(name = "valorTotal", nullable = true)
	private float valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "idVehiculo", nullable = false)
	private VehiculoEntidad vehiculo;
	
	@Column(name = "estado", nullable = true)
	private boolean estado;

	public FacturaEntidad() {
	}	

	public FacturaEntidad(int idFactura, Date fechaIngreso, Date fechaSalida, float valorTotal,
			VehiculoEntidad vehiculo, boolean estado) {
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

	public VehiculoEntidad getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntidad vehiculo) {
		this.vehiculo = vehiculo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
