package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.excepcion.SinContenidoExcepcion;
import com.ceiba.Parking.infraestructura.persistencia.mapper.VehiculoMapper;

@Repository
@Transactional
public class VehiculoRepositorio implements IVehiculoRepositorio{
	
	private static final String LISTAVACIA = "No hay vehiculos registrados";
	private final IVehiculoJPA vehiculoJPA;
	
	@Override
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {		
		return VehiculoMapper.convertirADominio(vehiculoJPA.save(VehiculoMapper.convertirAEntidad(vehiculo)));
	}
	
	@Override
	public List<Vehiculo> obtenerVehiculos() {
		List<Vehiculo> vehiculos = this.vehiculoJPA.findAll().stream().map(VehiculoMapper::convertirADominio)
				.collect(Collectors.toList());
		if (vehiculos.isEmpty()) {
			throw new SinContenidoExcepcion(LISTAVACIA);
		}
		
		return vehiculos;
	}
	
	public VehiculoRepositorio(final IVehiculoJPA vehiculoJPA) {
		this.vehiculoJPA = vehiculoJPA;
	}

}
