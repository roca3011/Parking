package com.ceiba.parking.infraestructura.persistencia.servicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.parking.dominio.repositorio.ITipoVehiculoRepositorio;
import com.ceiba.parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.parking.dominio.servicio.ServicioParqueadero;

@Configuration
public class BeanServicio {	

	@Bean
	public ServicioParqueadero servicioParqueadero(IVehiculoRepositorio vehiculoRepositorio, ITipoVehiculoRepositorio tipoVehiculoRepositorio, IFacturaRepositorio facturaRepositorio) {
		return new ServicioParqueadero(vehiculoRepositorio, tipoVehiculoRepositorio, facturaRepositorio);
	}
}
