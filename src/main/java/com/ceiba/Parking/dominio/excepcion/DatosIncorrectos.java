package com.ceiba.Parking.dominio.excepcion;

public class DatosIncorrectos extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DatosIncorrectos(String mensaje) {
		super(mensaje);
	}

}
