package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceiba.Parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;

@Repository
public interface IVehiculoJPA extends JpaRepository<VehiculoEntidad, Long>{
	
	List <VehiculoEntidad> findByTipoVehiculoEntidad(TipoVehiculoEntidad tipoVehiculo);
	
	@Query (value = "SELECT v.placa, f.fecha_Ingreso, t.descripcion  FROM vehiculo v, factura f, tipo_Vehiculo t where f.id_vehiculo = v.id_vehiculo and t.tipo_vehiculo_id = v.tipo_vehiculo_id and v.activo = TRUE", nativeQuery = true)
	List<String> darVehiculosIngresados();

}
