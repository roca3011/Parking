package com.ceiba.parking.infraestructura.persistencia.mapper;

import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.infraestructura.persistencia.entidad.FacturaEntidad;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculoEntidad;

public class FacturaMapper {
	
	private FacturaMapper() {
	}
	
	public static Factura convertirADominio(FacturaEntidad facturaEntidad) {
		Factura factura = new Factura();
		factura.setIdFactura(facturaEntidad.getIdFactura());
		factura.setFechaIngreso(facturaEntidad.getFechaIngreso());
		factura.setFechaSalida(facturaEntidad.getFechaSalida());
		factura.setValorTotal(facturaEntidad.getValorTotal());
		Vehiculo vehiculo = VehiculoMapper.convertirADominio(facturaEntidad.getVehiculo());
		factura.setVehiculo(vehiculo);
		factura.setEstado(facturaEntidad.isEstado());
		
		return factura;
	}
	
	public static FacturaEntidad convertirAEntidad(Factura factura) {
		FacturaEntidad facturaEntidad = new FacturaEntidad();
		facturaEntidad.setIdFactura(factura.getIdFactura());
		facturaEntidad.setFechaIngreso(factura.getFechaIngreso());
		facturaEntidad.setFechaSalida(factura.getFechaSalida());
		facturaEntidad.setValorTotal(factura.getValorTotal());
		VehiculoEntidad vehiculoEntidad = VehiculoMapper.convertirAEntidad(factura.getVehiculo());
		facturaEntidad.setVehiculo(vehiculoEntidad);
		facturaEntidad.setEstado(factura.isEstado());
		
		return facturaEntidad;
	}

}
