package com.ceiba.parking.databuilder;

import java.util.Date;

import com.ceiba.parking.dominio.modelo.Factura;
import com.ceiba.parking.dominio.modelo.Vehiculo;

public class FacturaTestDataBuilder {
	
	private static final long IDFACTURA = 1;
	private static final Date FECHAINGRESO = new Date();
	private static final Date FECHASALIDA = new Date();
	private static final float VALORTOTAL = 0.0f;
	private static final Vehiculo VEHICULO = new VehiculoTestDataBuilder().build();
	private static final boolean ESTADO = true;
		
	public Factura build() {
		return new Factura(IDFACTURA, FECHAINGRESO, FECHASALIDA, VALORTOTAL, VEHICULO, ESTADO);
	}
	
}
