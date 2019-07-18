package com.ceiba.Parking.dominio.repositorio;

import java.util.List;

import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;

public interface IFacturaRepositorio {
	
	public List<Factura> obtenerFacturasPorVehiculo(Vehiculo vehiculo);
	
	public Factura registrarEntrada(Factura factura);
	
	public Factura registrarSalida(Vehiculo vehiculo);
	
	public Factura obtenerFacturaActiva(Vehiculo vehiculo);
	
	public Factura actualizarEntidadFactura(Factura factura);

}
