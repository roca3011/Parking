package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.Parking.infraestructura.persistencia.entidad.FacturaEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;

public interface IFacturaJPA extends JpaRepository<FacturaEntidad, Long>{

	List<FacturaEntidad> findByVehiculo(VehiculoEntidad vehiculo); 
}
