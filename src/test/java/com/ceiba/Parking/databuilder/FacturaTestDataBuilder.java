package com.ceiba.Parking.databuilder;

import java.util.Date;

import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;

public class FacturaTestDataBuilder {
	
	private static final long IDFACTURA = 1;
	private static final Date FECHAINGRESO = new Date();
	private static final Date FECHASALIDA = new Date();
	private static final float VALORTOTAL = 0.0f;
	private static final Vehiculo VEHICULO = new VehiculoTestDataBuilder().build();
	private static final boolean ESTADO = true;
	
	private long idFactura;
	private Date fechaIngreso;
	private Date fechaSalida;
	private float valorTotal;
	private Vehiculo vehiculo;
	private boolean estado;
	
	public FacturaTestDataBuilder() {
		this.idFactura = IDFACTURA;
		this.fechaIngreso = FECHAINGRESO;
		this.fechaSalida = FECHASALIDA;
		this.valorTotal = VALORTOTAL;
		this.vehiculo = VEHICULO;
		this.estado = ESTADO;
	}
	
	public Factura build() {
		return new Factura(IDFACTURA, FECHAINGRESO, FECHASALIDA, VALORTOTAL, VEHICULO, ESTADO);
	}
	
}
