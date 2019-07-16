package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.Parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;

public interface IVehiculoJPA extends JpaRepository<VehiculoEntidad, Long>{
	
	List <VehiculoEntidad> findByTipoVehiculoEntidad(TipoVehiculoEntidad tipoVehiculo);

}
