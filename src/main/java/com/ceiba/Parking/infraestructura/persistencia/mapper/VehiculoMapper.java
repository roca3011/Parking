package com.ceiba.parking.infraestructura.persistencia.mapper;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculoEntidad;

public class VehiculoMapper {
	
	private VehiculoMapper() {
	}
	
	public static Vehiculo convertirADominio(VehiculoEntidad vehiculoEntidad) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo(vehiculoEntidad.getIdVehiculo());
		TipoVehiculo tipoVehiculo = TipoVehiculoMapper.convertirADominio(vehiculoEntidad.getTipoVehiculoEntidad());
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculo.setCilindraje(vehiculoEntidad.getCilindraje());
		vehiculo.setPlaca(vehiculoEntidad.getPlaca());
		vehiculo.setFechaCreacion(vehiculoEntidad.getFechaCreacion());
		
		return vehiculo;
	}
	
	public static VehiculoEntidad convertirAEntidad(Vehiculo vehiculo) {
		VehiculoEntidad vehiculoEntidad = new VehiculoEntidad();
		vehiculoEntidad.setIdVehiculo(vehiculo.getIdVehiculo());
		TipoVehiculoEntidad tipoVehiculoEntidad = TipoVehiculoMapper.convertirAEntidad(vehiculo.getTipoVehiculo());
		vehiculoEntidad.setTipoVehiculoEntidad(tipoVehiculoEntidad);
		vehiculoEntidad.setCilindraje(vehiculo.getCilindraje());
		vehiculoEntidad.setPlaca(vehiculo.getPlaca());
		vehiculoEntidad.setFechaCreacion(vehiculo.getFechaCreacion());
		
		return vehiculoEntidad;
	}

}
