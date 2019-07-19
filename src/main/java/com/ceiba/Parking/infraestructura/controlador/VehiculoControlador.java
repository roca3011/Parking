package com.ceiba.parking.infraestructura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.aplicacion.IngresarVehiculo;
import com.ceiba.parking.aplicacion.ObtenerVehiculos;
import com.ceiba.parking.aplicacion.VehiculoServicio;
import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;

@RestController
@RequestMapping("/parqueadero")
public class VehiculoControlador {	
	
	private IngresarVehiculo ingresarVehiculo;	
	private ObtenerVehiculos obtenerVehiculos;
	private VehiculoServicio vehiculoServicio;
	
	@PostMapping
	public ResponseEntity<Vehiculo> registroVehiculo(@RequestBody Vehiculo vehiculo){
		return new ResponseEntity<>(ingresarVehiculo.registroVehiculo(vehiculo),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/vehiculos")
	public  ResponseEntity<List<Vehiculo>> listaDeVehiculos() {
		return new ResponseEntity<>(obtenerVehiculos.obtenerVehiculos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/vehiculos/activos")
	public  ResponseEntity<List<VehiculosActivos>> obtenerVehiculosActivos() {
		return new ResponseEntity<>(obtenerVehiculos.obtenerVehiculosActivos(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/vehiculo/facturas")
	public ResponseEntity<List<Factura>> obtenerFacturasVehiculo(@RequestBody Vehiculo vehiculo) {
		return new ResponseEntity<>(vehiculoServicio.obtenerFacturasVehiculo(vehiculo), HttpStatus.OK);
	}
	
	@PostMapping(value = "/vehiculo/factura")
	public ResponseEntity<Factura> obtenerFacturaVehiculo(@RequestBody Vehiculo vehiculo) {
		return new ResponseEntity<>(vehiculoServicio.obtenerFacturaVehiculo(vehiculo), HttpStatus.OK);
	}
	
	
	public VehiculoControlador(IngresarVehiculo ingresarVehiculo, ObtenerVehiculos obtenerVehiculos, VehiculoServicio vehiculoServicio) {
		this.ingresarVehiculo = ingresarVehiculo;
		this.obtenerVehiculos = obtenerVehiculos;
		this.vehiculoServicio = vehiculoServicio;
	}

}
