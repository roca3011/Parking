package com.ceiba.parking.infraestructura.controlador;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.aplicacion.IngresarVehiculo;
import com.ceiba.parking.aplicacion.ObtenerVehiculos;
import com.ceiba.parking.aplicacion.VehiculoServicio;
import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;
import com.ceiba.parking.infraestructura.persistencia.entidad.VehiculoEntidad;

@CrossOrigin//(origins = { "http://localhost:4200" })
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
	
	@GetMapping(value = "/vehiculos/tipos_vehiculo")
	public  ResponseEntity<List<TipoVehiculo>> obtenerTiposVehiculo() {
		return new ResponseEntity<>(vehiculoServicio.obtenerTiposVehiculo(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/vehiculo/factura/{placa}")
	public  ResponseEntity<Factura> obtenerfactura(@PathVariable String placa) {
		//Factura factura = new Factura();
		//factura.setValorTotal(20000);
		return new ResponseEntity<>(vehiculoServicio.obtenerFactura(placa), HttpStatus.OK);
	}
	
	
	public VehiculoControlador(IngresarVehiculo ingresarVehiculo, ObtenerVehiculos obtenerVehiculos, VehiculoServicio vehiculoServicio) {
		this.ingresarVehiculo = ingresarVehiculo;
		this.obtenerVehiculos = obtenerVehiculos;
		this.vehiculoServicio = vehiculoServicio;
	}

}
