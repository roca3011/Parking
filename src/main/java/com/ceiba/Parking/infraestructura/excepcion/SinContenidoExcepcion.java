package com.ceiba.parking.infraestructura.excepcion;

public class SinContenidoExcepcion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SinContenidoExcepcion(String mensaje) {
		super(mensaje);
	}
}
