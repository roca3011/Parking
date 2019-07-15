package com.ceiba.Parking.infraestructura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.Parking.aplicacion.servicio.VehiculoServicio;
import com.ceiba.Parking.dominio.modelo.Vehiculo;

@RestController
@RequestMapping("/parqueadero")
public class VehiculoControlador {
	
	private VehiculoServicio vehiculoServicio;
	
	@PostMapping
	public ResponseEntity<Vehiculo> registroVehiculo(@RequestBody Vehiculo vehiculo){
		vehiculoServicio.registroVehiculo(vehiculo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/vehiculos")
	public  ResponseEntity<List<Vehiculo>> listaDeVehiculos() {
		return new ResponseEntity<>(vehiculoServicio.obtenerVehiculos(), HttpStatus.OK);
	}
	
	public VehiculoControlador(VehiculoServicio vehiculoServicio) {
		this.vehiculoServicio = vehiculoServicio;
	}


}
