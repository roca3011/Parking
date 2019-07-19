package com.ceiba.parking.infraestructura.persistencia.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.parking.infraestructura.excepcion.SinContenidoExcepcion;
import com.ceiba.parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.parking.infraestructura.persistencia.mapper.TipoVehiculoMapper;

@Repository
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
