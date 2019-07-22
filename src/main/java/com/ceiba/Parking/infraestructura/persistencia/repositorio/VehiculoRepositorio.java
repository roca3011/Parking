package com.ceiba.parking.infraestructura.persistencia.repositorio;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;
import com.ceiba.parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.parking.infraestructura.excepcion.SinContenidoExcepcion;
import com.ceiba.parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculoEntidad;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculosActivosProyeccion;
import com.ceiba.parking.infraestructura.persistencia.mapper.TipoVehiculoMapper;
import com.ceiba.parking.infraestructura.persistencia.mapper.VehiculoMapper;
import com.ceiba.parking.infraestructura.persistencia.mapper.VehiculosActivosMapper;

@Repository
public class VehiculoRepositorio implements IVehiculoRepositorio{
	
	private static final String LISTAVACIA = "No hay vehiculos registrados";
	private final IVehiculoJPA vehiculoJPA;
	
	public VehiculoRepositorio(final IVehiculoJPA vehiculoJPA) {
		this.vehiculoJPA = vehiculoJPA;
	}
	
	@Override
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {		
		return VehiculoMapper.convertirADominio(vehiculoJPA.saveAndFlush(VehiculoMapper.convertirAEntidad(vehiculo)));
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

	@Override
	public int cantidadVehiculos(TipoVehiculo tipoVehiculo) {
		TipoVehiculoEntidad tipoVehiculoEntidad = TipoVehiculoMapper.convertirAEntidad(tipoVehiculo);
		List<Vehiculo> vehiculosEntidad = this.vehiculoJPA.findByTipoVehiculoEntidadIdTipoVehiculo(tipoVehiculoEntidad.getIdTipoVehiculo()).stream().map(VehiculoMapper::convertirADominio)
				.collect(Collectors.toList());
		
		return vehiculosEntidad.size();
	}

	@Override
	public List<VehiculosActivos> vehiculosParqueadero() {		
		
		List<VehiculosActivosProyeccion> vehiculosActivosProyeccion = vehiculoJPA.obtenerVehiculosActivos();
				
		if (vehiculosActivosProyeccion == null) {
			return Collections.emptyList();
		}
		
		return  vehiculosActivosProyeccion.stream().map(VehiculosActivosMapper::convertirProyeccionaEntidad)
				.collect(Collectors.toList());
	}
	
	@Override
	public Vehiculo obtenerVehiculoPorplaca(String placa) {		
		VehiculoEntidad vehiculoEntidad;
		vehiculoEntidad =  vehiculoJPA.findByPlacaIgnoreCase(placa);
		
		if (vehiculoEntidad == null) {
			return null;
		}		 		
		return VehiculoMapper.convertirADominio(vehiculoEntidad);
	}
	
}
