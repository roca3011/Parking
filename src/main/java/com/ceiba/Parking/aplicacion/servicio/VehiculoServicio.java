package com.ceiba.Parking.aplicacion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.Parking.dominio.modelo.Parqueadero;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.servicio.ServicioRegistrarVehiculo;

@Service
public class VehiculoServicio implements IngresarVehiculo, ObtenerVehiculos{
	
	@Autowired
	private Parqueadero parqueadero;
		
	@Override
	public Vehiculo registroVehiculo(Vehiculo vehiculo) {
		return parqueadero.registroVehiculo(vehiculo);
	}
	
	@Override
	public List<Vehiculo> obtenerVehiculos(){
		return parqueadero.obtenerVehiculos();
	}

}
