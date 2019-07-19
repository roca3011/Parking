package com.ceiba.parking.dominio.repositorio;

import java.util.List;

import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.Vehiculo;

public interface IFacturaRepositorio {
	
	public List<Factura> obtenerFacturasPorVehiculo(Vehiculo vehiculo);
	
	public Factura registrarEntrada(Factura factura);
	
	public Factura registrarSalida(Vehiculo vehiculo);
	
	public Factura obtenerFacturaActiva(Vehiculo vehiculo);
	
	public Factura actualizarEntidadFactura(Factura factura);

}
