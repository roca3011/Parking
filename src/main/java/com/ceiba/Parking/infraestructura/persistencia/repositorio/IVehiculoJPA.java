package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivosProyeccion;
import com.ceiba.Parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivosEntidad;

@Repository
public interface IVehiculoJPA extends JpaRepository<VehiculoEntidad, Long>{
	
	List <VehiculoEntidad> findByTipoVehiculoEntidad(TipoVehiculoEntidad tipoVehiculo);
	
	//@Query (value = "SELECT v.id_vehiculo as id, v.placa as placa,to_char(f.fecha_Ingreso) as fechaIngreso, t.descripcion as descripcion  FROM VehiculoEntidad v, FacturaEntidad f, TipoVehiculoEntidad t where f.id_vehiculo = v.id_vehiculo and t.tipo_vehiculo_id = v.tipo_vehiculo_id and f.estado = FALSE")
	@Query (value = "SELECT  v.placa as placa,to_char(f.fecha_Ingreso) as fechaIngreso, t.descripcion as descripcion  FROM vehiculo v, factura f, tipo_Vehiculo t where f.id_vehiculo = v.id_vehiculo and t.tipo_vehiculo_id = v.tipo_vehiculo_id and f.estado = TRUE", nativeQuery = true)
	List<VehiculosActivosProyeccion> obtenerVehiculosActivos();
	
	VehiculoEntidad findByPlaca(String placa);

}
