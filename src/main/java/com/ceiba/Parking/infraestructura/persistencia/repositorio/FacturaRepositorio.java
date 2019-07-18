package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.Parking.infraestructura.persistencia.entidad.FacturaEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.mapper.FacturaMapper;
import com.ceiba.Parking.infraestructura.persistencia.mapper.VehiculoMapper;

@Repository
public class FacturaRepositorio implements IFacturaRepositorio{
	
	private final IFacturaJPA facturaJPA;
	
	public FacturaRepositorio(final IFacturaJPA facturaJpa) {
		this.facturaJPA = facturaJpa;
	}

	@Override
	public List<Factura> obtenerFacturasPorVehiculo(Vehiculo vehiculo) {
		List<Factura> facturas;
		VehiculoEntidad vehiculoEntidad = VehiculoMapper.convertirAEntidad(vehiculo);		
		facturas = facturaJPA.findByVehiculo(vehiculoEntidad).stream().map(FacturaMapper::convertirADominio)
				.collect(Collectors.toList());
		
		return facturas;
	}

	@Override
	public Factura registrarEntrada(Factura factura) {
		FacturaEntidad facturaEntidad = FacturaMapper.convertirAEntidad(factura);
		return FacturaMapper.convertirADominio(facturaJPA.saveAndFlush(facturaEntidad));
	}

	@Override
	public Factura registrarSalida(Vehiculo vehiculo) {		
		return obtenerFacturaActiva(vehiculo);
	}

	@Override
	public Factura obtenerFacturaActiva(Vehiculo vehiculo) {
		Factura factura = null;
		VehiculoEntidad vehiculoEntidad = VehiculoMapper.convertirAEntidad(vehiculo);
		FacturaEntidad facturaEntidad = facturaJPA.findByVehiculoAndEstado(vehiculoEntidad, true);
		if (facturaEntidad != null) {
			factura = FacturaMapper.convertirADominio(facturaEntidad);
		}		
		return factura;
	}

	@Override
	public Factura actualizarEntidadFactura(Factura factura) {
		FacturaEntidad facturaEntidad= FacturaMapper.convertirAEntidad(factura);
		facturaJPA.updateFactura(facturaEntidad.getFechaSalida(), facturaEntidad.getValorTotal(), false, facturaEntidad.getIdFactura());
		return FacturaMapper.convertirADominio(facturaEntidad);
	}

}
