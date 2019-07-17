package com.ceiba.Parking.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.Parking.dominio.modelo.TipoVehiculo;
import com.ceiba.Parking.dominio.modelo.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final long IDVEHICULO = 1;
	private static final TipoVehiculo TIPOVEHICULO = new TipoVehiculoDataBuilder().build();
	private static final int CILINDRAJE = 1100;
	private static final String PLACA = "FBH343";
	private static final boolean ACTIVO = false;
	private static final Date FECHACREACION = new Date();
	
	private long idVehiculo;
	private TipoVehiculo tipoVehiculo;
	private int cilindraje;
	private String placa;

	public VehiculoTestDataBuilder(long idVehiculo, TipoVehiculo tipoVehiculo, int cilindraje, String placa) {
		this.idVehiculo = idVehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.placa = placa;
	}

	public VehiculoTestDataBuilder() {
		this.idVehiculo = IDVEHICULO;
		this.tipoVehiculo = TIPOVEHICULO;
		this.cilindraje = CILINDRAJE;
		this.placa = PLACA;
	}

	public VehiculoTestDataBuilder vehiculo(TipoVehiculo tipoVehiculo) {		
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(IDVEHICULO, tipoVehiculo, CILINDRAJE, PLACA, obtenerFechaActual());
		
	}
	
	public Date obtenerFechaActual() {
		Date hoy=new Date();
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);
	    
	    return calendario.getTime();
	}

}
