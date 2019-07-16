package com.ceiba.Parking.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.Parking.databuilder.TipoVehiculoDataBuilder;
import com.ceiba.Parking.databuilder.VehiculoTestDataBuilder;
import com.ceiba.Parking.dominio.excepcion.AccesoDenegadoException;
import com.ceiba.Parking.dominio.modelo.Parqueadero;
import com.ceiba.Parking.dominio.modelo.TipoVehiculo;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.Parking.infraestructura.persistencia.repositorio.TipoVehiculoRepositorio;

public class ParqueaderoTest {
	
	private static final long IDVEHICULO = 1;
	private static final TipoVehiculo TIPOVEHICULO = new TipoVehiculoDataBuilder().build();
	private static final int CILINDRAJE = 1100;
	private static final String PLACA = "FBH343";
	private static final String DESCRIPCION = "Camioneta";
	private static final int LIMITECUPOSCARROS = 20;
	private static final String CARRO = "Carro";
	
	VehiculoTestDataBuilder vehiculoTestBuilder;
	Parqueadero parqueadero;
	Vehiculo vehiculo;
	
	@Before
	public void setup() {
		vehiculoTestBuilder = new VehiculoTestDataBuilder();
		parqueadero = new Parqueadero();
	}
	
	@Test
	public void crearVehiculo() {
		// arrange
		VehiculoTestDataBuilder vehiculoTestBuilder = new VehiculoTestDataBuilder();
		// act
		vehiculo = vehiculoTestBuilder.build();

		// assert
		assertEquals(IDVEHICULO, vehiculo.getIdVehiculo());
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		assertEquals(TIPOVEHICULO.getDescripcion(), vehiculo.getTipoVehiculo().getDescripcion());		
	}
	
	@Test
	public void registroVehiculoExitoso() {
		
		// arrange
		vehiculo = vehiculoTestBuilder.build();		
		Parqueadero parqueadero = mock(Parqueadero.class);		

		// act
		when(parqueadero.registroVehiculo(vehiculo)).thenReturn(vehiculo);
		
		// assert
		assertNotNull("Registro éxitoso", parqueadero.registroVehiculo(vehiculo));
		
	}
	
	@Test
	public void obtenerTipoDeVehiculoPorDescripcionRetornaNulo() {		
		// arrange				
		TipoVehiculoRepositorio tipoVehiculoRepositorio = mock(TipoVehiculoRepositorio.class);
		
		// act
		when(tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(DESCRIPCION)).thenReturn(null);		
		TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(DESCRIPCION);
		
		// assert
		assertNull(tipoVehiculo);
		
	}
	
	@Test//(expected = AccesoDenegadoException.class)
	public void obtenerCantidadDeVehiculosPorTipo() {	
		
		// arrange		
		IVehiculoRepositorio vehiculoRepositorio = mock(IVehiculoRepositorio.class);
		TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder().build();
			
		// act
		when(vehiculoRepositorio.cantidadVehiculos(tipoVehiculo)).thenReturn(LIMITECUPOSCARROS);
		
		assertEquals(LIMITECUPOSCARROS, vehiculoRepositorio.cantidadVehiculos(tipoVehiculo));
						
	}
	
	@Test(expected = AccesoDenegadoException.class)
	public void validarCantidadDeCuposPorTipoDeVehiculoRetornaExcepcion() {	
		
		// arrange		
		IVehiculoRepositorio vehiculoRepositorio = mock(IVehiculoRepositorio.class);
		TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder().build();
					
		// act
		when(vehiculoRepositorio.cantidadVehiculos(tipoVehiculo)).thenReturn(LIMITECUPOSCARROS);
		int cantidadVehiculosPorTipo = vehiculoRepositorio.cantidadVehiculos(tipoVehiculo);
		
		parqueadero.validarCupo(cantidadVehiculosPorTipo, 10);
						
	}
	
	@Test
	public void validaSiExisteElTipoDeVehiculoIngresadoRetornaNull() {	
		
		// arrange
		TipoVehiculoRepositorio tipoVehiculoRepositorio = mock(TipoVehiculoRepositorio.class); 
		
		// act
		when(tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(CARRO)).thenReturn(null);
		
		//assert
		assertNull("No existe tipo el tipo de vehiculo", tipoVehiculoRepositorio.obtenerTipoVehiculoPorDesc(CARRO));
	}

}
