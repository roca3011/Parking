package com.ceiba.parking.infraestructura.persistencia.mapper;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.infraestructura.persistencia.entidad.TipoVehiculoEntidad;

public class TipoVehiculoMapper {

	private TipoVehiculoMapper() {
	}
	
	public static TipoVehiculo convertirADominio(TipoVehiculoEntidad tipoVehiculoEntidad) {
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setIdTipoVehiculo(tipoVehiculoEntidad.getIdTipoVehiculo());
		tipoVehiculo.setDescripcion(tipoVehiculoEntidad.getDescripcion());
		return tipoVehiculo;
	}
	
	public static TipoVehiculo convertirADominio(String descripcion) {
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setDescripcion(descripcion);
		return tipoVehiculo;
	}
	
	public static TipoVehiculoEntidad convertirAEntidad(TipoVehiculo tipoVehiculo) {
		TipoVehiculoEntidad tipoVehiculoEntidad = new TipoVehiculoEntidad();
		tipoVehiculoEntidad.setIdTipoVehiculo(tipoVehiculo.getIdTipoVehiculo());
		tipoVehiculoEntidad.setDescripcion(tipoVehiculo.getDescripcion());
		return tipoVehiculoEntidad;
	}
}
