package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.Parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;

public interface ITipoVehiculoJPA extends JpaRepository<TipoVehiculoEntidad, Long>{
	
	TipoVehiculoEntidad findByDescripcionIgnoreCase(String descripcion);

}
