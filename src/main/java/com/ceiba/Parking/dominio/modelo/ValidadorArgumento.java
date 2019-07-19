package com.ceiba.parking.dominio.modelo;

import com.ceiba.parking.dominio.excepcion.ExcepcionCampoObligatorio;
import com.ceiba.parking.dominio.excepcion.ExcepcionLongitudValor;

public class ValidadorArgumento {
	
	private ValidadorArgumento() {
	}
	
	public static void validadorCampoObligatorio(Object valor, String mensaje) {
		if (valor == null) {
			throw new ExcepcionCampoObligatorio(mensaje);
		}
	}
	
	public static void validarLongitud(String valor,int longitud,String mensaje){
        if(valor.length() < longitud){
            throw new ExcepcionLongitudValor(mensaje);
        }
    }

}
