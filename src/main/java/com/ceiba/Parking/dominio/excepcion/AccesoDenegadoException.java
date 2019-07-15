package com.ceiba.Parking.dominio.excepcion;

public class AccesoDenegadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccesoDenegadoException(String mensaje) {
		super(mensaje);
	}

}
