package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.Parking.dominio.modelo.TipoVehiculo;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.excepcion.SinContenidoExcepcion;
import com.ceiba.Parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivos;
import com.ceiba.Parking.infraestructura.persistencia.mapper.TipoVehiculoMapper;
import com.ceiba.Parking.infraestructura.persistencia.mapper.VehiculoMapper;

@Repository
public class VehiculoRepositorio implements IVehiculoRepositorio{
	
	private static final String LISTAVACIA = "No hay vehiculos registrados";
	private static final String SEPARADOR = ",";
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
		List<Vehiculo> vehiculosEntidad = this.vehiculoJPA.findByTipoVehiculoEntidad(tipoVehiculoEntidad).stream().map(VehiculoMapper::convertirADominio)
				.collect(Collectors.toList());
		
		return vehiculosEntidad.size();
	}

	@Override
	public List<VehiculosActivos> vehiculosParqueadero() {
		List<VehiculosActivos> listavehiculosActivos = new ArrayList<>();
		List<String> vehiculosActivos = vehiculoJPA.obtenerVehiculosActivos();
		
		//--------------x
		String[] parametros = null;
		for (String vehiculoActivo : vehiculosActivos) {
			parametros = vehiculoActivo.split(SEPARADOR);	
			
			VehiculosActivos vehiculosActivosEntidad = vehiculosActivosMapper(parametros);
			
			if (vehiculosActivosEntidad != null) {
				listavehiculosActivos.add(vehiculosActivosEntidad);
			}			
		}			
		return listavehiculosActivos;
	}
	
	@Override
	public Vehiculo obtenerVehiculoPorplaca(String placa) {		
		VehiculoEntidad vehiculoEntidad;
		vehiculoEntidad =  vehiculoJPA.findByPlaca(placa);
		
		if (vehiculoEntidad == null) {
			return null;
		}		 		
		return VehiculoMapper.convertirADominio(vehiculoEntidad);
	}
	
	public  VehiculosActivos vehiculosActivosMapper(String[] parametros) {
		VehiculosActivos vehiculosActivos =  new VehiculosActivos();
		
		int numeroParametros = parametros.length;
		if (numeroParametros>0 && numeroParametros==3) {
			vehiculosActivos.setPlaca(parametros[0]);
			vehiculosActivos.setFechaIngreso(parametros[1]);
			vehiculosActivos.setDescripcion(parametros[2]);
		}
		
		return vehiculosActivos;
	}

}
