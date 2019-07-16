package com.ceiba.Parking.infraestructura.persistencia.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.Parking.infraestructura.persistencia.entidad.FacturaEntidad;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculoEntidad;
import com.ceiba.Parking.infraestructura.persistencia.mapper.FacturaMapper;
import com.ceiba.Parking.infraestructura.persistencia.mapper.VehiculoMapper;

@Repository
@Transactional
public class FacturaRepositorio implements IFacturaRepositorio{
	
	private final IFacturaJPA facturaJPA;
	
	public FacturaRepositorio(final IFacturaJPA facturaJpa) {
		this.facturaJPA = facturaJpa;
	}

	@Override
	public List<Factura> obtenerFacturasPorVehiculo(Vehiculo vehiculo) {
		VehiculoEntidad vehiculoEntidad = VehiculoMapper.convertirAEntidad(vehiculo);
		List<Factura> facturas = facturaJPA.findByVehiculo(vehiculoEntidad).stream().map(FacturaMapper::convertirADominio)
				.collect(Collectors.toList());
		
		return facturas;
	}

	@Override
	public Factura registrarEntrada(Factura factura) {
		FacturaEntidad facturaEntidad = FacturaMapper.convertirAEntidad(factura);
		return FacturaMapper.convertirADominio(facturaJPA.save(facturaEntidad));
	}

	@Override
	public Factura registrarSalida(Factura factura) {
		// TODO Auto-generated method stub
		return null;
	}

}
