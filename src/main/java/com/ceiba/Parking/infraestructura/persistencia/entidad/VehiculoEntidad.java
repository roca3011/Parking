package com.ceiba.Parking.infraestructura.persistencia.entidad;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehiculo")
public class VehiculoEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVehiculo")
	private long idVehiculo;
	
	@ManyToOne
	@JoinColumn(name = "tipo_vehiculo_id", nullable = false)
	private TipoVehiculoEntidad tipoVehiculoEntidad;
	
	@Column(name = "cilindraje", nullable = false)
	private int cilindraje;
	
	@Column(name = "placa", nullable = false)
	private String placa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vehiculo")
	private Set<FacturaEntidad> facturas;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaCreacion", nullable = true)
	private Date fechaCreacion;

	public long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public TipoVehiculoEntidad getTipoVehiculoEntidad() {
		return tipoVehiculoEntidad;
	}

	public void setTipoVehiculoEntidad(TipoVehiculoEntidad tipoVehiculoEntidad) {
		this.tipoVehiculoEntidad = tipoVehiculoEntidad;
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
