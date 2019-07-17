package com.ceiba.Parking.dominio.modelo;

import com.ceiba.Parking.dominio.excepcion.ExcepcionCampoObligatorio;
import com.ceiba.Parking.dominio.excepcion.ExcepcionLongitudValor;

public class ValidadorArgumento {
	
	public ValidadorArgumento() {}
	
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
