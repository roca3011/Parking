package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.Parking.dominio.modelo.TipoVehiculo;
import com.ceiba.Parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.excepcion.SinContenidoExcepcion;
import com.ceiba.Parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.mapper.TipoVehiculoMapper;

@Repository
@Transactional
public class TipoVehiculoRepositorio implements ITipoVehiculoRepositorio{
	
	private static final String LISTAVACIA = "No hay tipo de vehiculos registrados";
	private final ITipoVehiculoJPA tipoVehiculoJPA;

	@Override
	public List<TipoVehiculo> obtenerTiposDeVehiculo() {
		List<TipoVehiculo> tipoVehiculos = tipoVehiculoJPA.findAll().stream().map(TipoVehiculoMapper::convertirADominio)
				.collect(Collectors.toList());
		if (tipoVehiculos.isEmpty()) {
			throw new SinContenidoExcepcion(LISTAVACIA); 
		}
		
		return tipoVehiculos;
	}

	@Override
	public TipoVehiculo obtenerTipoVehiculoPorDesc(String descripcion) {
		
		TipoVehiculoEntidad tipoVehiculoEntidad = tipoVehiculoJPA.findByDescripcionIgnoreCase(descripcion);
		if (tipoVehiculoEntidad == null) {
			return null;
		}
		return TipoVehiculoMapper.convertirADominio(tipoVehiculoEntidad);
		
	}
	
	public TipoVehiculoRepositorio(ITipoVehiculoJPA tipoVehiculoJPA) {
		this.tipoVehiculoJPA = tipoVehiculoJPA;
	}

}
