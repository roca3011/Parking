package com.ceiba.parking.dominio.excepcion;

public class ExcepcionFactura extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExcepcionFactura(String mensaje) {
		super(mensaje);
	}
	
}
