package com.ceiba.Parking.dominio.excepcion;

public class ExcepcionCampoObligatorio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionCampoObligatorio(String mensaje) {
		super(mensaje);
	}

}
