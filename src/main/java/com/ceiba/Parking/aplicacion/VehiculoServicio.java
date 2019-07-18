package com.ceiba.Parking.aplicacion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.modelo.VehiculosActivos;
import com.ceiba.Parking.dominio.servicio.ServicioParqueadero;

@Service
@Transactional
public class VehiculoServicio implements IngresarVehiculo, ObtenerVehiculos{
	
	private ServicioParqueadero parqueadero;
	
	public VehiculoServicio(ServicioParqueadero servicioParqueadero) {
		this.parqueadero = servicioParqueadero;
	}
		
	@Override
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {
		return parqueadero.registroVehiculo(vehiculo);
	}
	
	@Override
	public List<Vehiculo> obtenerVehiculos(){
		return parqueadero.obtenerVehiculos();
	}

	@Override
	public List<VehiculosActivos> obtenerVehiculosActivos() {
		return parqueadero.obtenerVehiculosActivos();
	}
	
	public List<Factura> obtenerFacturasVehiculo(Vehiculo vehiculo){
		return parqueadero.obtenerFacturasVehiculo(vehiculo);
	}
	
	public Factura obtenerFacturaVehiculo(Vehiculo vehiculo){
		return parqueadero.obtenerFacturaVehiculo(vehiculo);
	}

}
