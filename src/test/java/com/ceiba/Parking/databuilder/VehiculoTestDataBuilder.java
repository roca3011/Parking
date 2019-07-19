package com.ceiba.parking.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final long IDVEHICULO = 1;
	private static final TipoVehiculo TIPOVEHICULO = new TipoVehiculoDataBuilder().build();
	private static final int CILINDRAJEVEHICULO = 1100;
	private static final String PLACA = "FBH343";
	
	private long id_Vehiculo;
	private TipoVehiculo tipo_Vehiculo;
	private int cilindraje_Vehiculo;
	private String placa_vehiculo;

	public VehiculoTestDataBuilder(long idVehiculo, TipoVehiculo tipoVehiculo, int cilindraje, String placa) {
		this.id_Vehiculo = idVehiculo;
		this.tipo_Vehiculo = tipoVehiculo;
		this.cilindraje_Vehiculo = cilindraje;
		this.placa_vehiculo = placa;
	}

	public VehiculoTestDataBuilder() {
		this.id_Vehiculo = IDVEHICULO;
		this.tipo_Vehiculo = TIPOVEHICULO;
		this.cilindraje_Vehiculo = CILINDRAJEVEHICULO;
		this.placa_vehiculo = PLACA;
	}

	public VehiculoTestDataBuilder vehiculo(TipoVehiculo tipoVehiculo) {		
		this.tipo_Vehiculo = tipoVehiculo;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(IDVEHICULO, tipo_Vehiculo, CILINDRAJEVEHICULO, PLACA, obtenerFechaActual());
		
	}
	
	public Date obtenerFechaActual() {
		Date hoy=new Date();
	    Calendar calendario= Calendar.getInstance();
	    calendario.setTime(hoy);
	    
	    return calendario.getTime();
	}

}
