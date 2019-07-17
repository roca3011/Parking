package com.ceiba.Parking.infraestructura.persistencia.servicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.Parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.Parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.dominio.servicio.ServicioParqueadero;

@Configuration
public class BeanServicio {	

	@Bean
	public ServicioParqueadero servicioParqueadero(IVehiculoRepositorio vehiculoRepositorio, ITipoVehiculoRepositorio tipoVehiculoRepositorio, IFacturaRepositorio facturaRepositorio) {
		return new ServicioParqueadero(vehiculoRepositorio, tipoVehiculoRepositorio, facturaRepositorio);
	}
}
