package com.ceiba.parking.infraestructura.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehiculosActivos")
public class VehiculosActivosEntidad implements VehiculosActivosProyeccion{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;	
	
	@NotNull
	private String placa; 
	
	@NotNull
	private String fechaIngreso;
	
	@NotNull
	private String descripcion;
	
	public VehiculosActivosEntidad() {
	}

	public VehiculosActivosEntidad(Long id, @NotNull String placa, @NotNull String fechaIngreso, @NotNull String descripcion) {
		super();
		this.id = id;
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}		

	public void setPlaca(String placa) {
		this.placa = placa;
	}	

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}	

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String getplaca() {		
		return this.placa;
	}

	@Override
	public String getfechaIngreso() {		
		return this.fechaIngreso;
	}

	@Override
	public String getdescripcion() {		
		return this.descripcion;
	}
	
	public VehiculosActivosEntidad obtenerVehiculosActivos() {
		return this;		
	}
	
	

}
