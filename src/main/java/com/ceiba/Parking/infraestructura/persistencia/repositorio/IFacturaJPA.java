package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceiba.Parking.infraestructura.persistencia.entidad.FacturaEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;

public interface IFacturaJPA extends JpaRepository<FacturaEntidad, Long>{

	List<FacturaEntidad> findByVehiculo(VehiculoEntidad vehiculo); 
	
	FacturaEntidad findByVehiculoAndEstado(VehiculoEntidad vehiculo, boolean estado);
	
	@Modifying
    @Query("Update FacturaEntidad t SET t.fechaSalida=:fechaSalida, t.valorTotal=:valorTotal, t.estado= :estado WHERE t.idFactura=:idFactura")
    public void updateFactura(@Param("fechaSalida") Date fechaSalida, @Param("valorTotal") float valorTotal, @Param("estado") boolean estado, @Param("idFactura") long idFactura);
}
