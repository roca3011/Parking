package com.ceiba.Parking.dominio.excepcion;

public class ExcepcionLongitudValor extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionLongitudValor(String mensaje) {
		super(mensaje);
	}

}
