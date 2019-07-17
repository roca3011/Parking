package com.ceiba.Parking.aplicacion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.servicio.ServicioParqueadero;
import com.ceiba.Parking.infraestructura.persistencia.entidad.VehiculosActivos;

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
	public List<VehiculosActivos> ObtenerVehiculosActivos() {
		return parqueadero.obtenerVehiculosActivos();
	}
	
	public List<Factura> obtenerFacturasVehiculo(Vehiculo vehiculo){
		return parqueadero.obtenerFacturasVehiculo(vehiculo);
	}

}
