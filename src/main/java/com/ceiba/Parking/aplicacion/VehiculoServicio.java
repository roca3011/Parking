package com.ceiba.parking.aplicacion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.modelo.VehiculosActivos;
import com.ceiba.parking.dominio.servicio.ServicioParqueadero;

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
	public List<VehiculosActivos> obtenerVehiculosActivos() {
		return parqueadero.obtenerVehiculosActivos();
	}
	
	public List<Factura> obtenerFacturasVehiculo(Vehiculo vehiculo){
		return parqueadero.obtenerFacturasVehiculo(vehiculo);
	}
	
	public Factura obtenerFacturaVehiculo(Vehiculo vehiculo){
		return parqueadero.obtenerFacturaVehiculo(vehiculo);
	}
	
	public List<TipoVehiculo> obtenerTiposVehiculo(){
		return parqueadero.obtenerTiposVehiculo();
	}
	
	public Factura obtenerFactura(String placa){
		return parqueadero.obtenerFactura(placa);
	}

}
