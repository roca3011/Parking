package com.ceiba.Parking.aplicacion.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.servicio.ServicioRegistrarVehiculo;

@Service
public class VehiculoServicio {
	
	private ServicioRegistrarVehiculo servicioRegistrarVehiculo;
		
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {
		return servicioRegistrarVehiculo.registroVehiculo(vehiculo);
	}
	
	public List<Vehiculo> obtenerVehiculos(){
		return servicioRegistrarVehiculo.obtenerVehiculos();
	}

	public VehiculoServicio(ServicioRegistrarVehiculo servicioRegistrarVehiculo) {
		this.servicioRegistrarVehiculo = servicioRegistrarVehiculo;
	}
}
