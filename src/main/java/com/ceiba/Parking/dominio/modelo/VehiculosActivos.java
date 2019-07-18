package com.ceiba.Parking.dominio.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

public class VehiculosActivos {
	
	private String placa; 

	private String fechaIngreso;

	private String descripcion;
	
	public VehiculosActivos() {
	}

	public VehiculosActivos(String placa, String fechaIngreso, String descripcion) {
		super();
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.descripcion = descripcion;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
