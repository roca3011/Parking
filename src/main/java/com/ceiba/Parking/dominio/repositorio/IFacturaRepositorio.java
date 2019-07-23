package com.ceiba.parking.dominio.repositorio;

import java.util.List;

import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.Vehiculo;

public interface IFacturaRepositorio {
	
	/**Obtiene una lista de facturas relacionadas a un objeto vehiculo*/
	public List<Factura> obtenerFacturasPorVehiculo(Vehiculo vehiculo);
	
	/** Genera la factura inicial al ingresar el vehiculo*/
	public Factura registrarEntrada(Factura factura);
	
	/**Genera la factura del vehiculo que sale*/
	public Factura registrarSalida(Vehiculo vehiculo);
	
	/** Obtiene la factura en estado activo de un objeto vehiculo*/
	public Factura obtenerFacturaActiva(Vehiculo vehiculo);
	
	/** Actualiza los datos de la factura*/
	public Factura actualizarEntidadFactura(Factura factura);

}
