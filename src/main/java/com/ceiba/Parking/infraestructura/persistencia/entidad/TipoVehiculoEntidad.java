package com.ceiba.Parking.infraestructura.persistencia.entidad;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipo_Vehiculo")
public class TipoVehiculoEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipo_vehiculo_id")
	private Long idTipoVehiculo;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipoVehiculoEntidad")
	private Set<VehiculoEntidad> vehiculos;

	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<VehiculoEntidad> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set<VehiculoEntidad> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
}
