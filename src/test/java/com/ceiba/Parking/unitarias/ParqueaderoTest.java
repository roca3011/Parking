package com.ceiba.parking.unitarias;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.parking.databuilder.TipoVehiculoDataBuilder;
import com.ceiba.parking.databuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.dominio.excepcion.AccesoDenegado;
import com.ceiba.parking.dominio.modelo.TipoVehiculo;
import com.ceiba.parking.dominio.modelo.Vehiculo;
import com.ceiba.parking.dominio.repositorio.IFacturaRepositorio;
import com.ceiba.parking.dominio.repositorio.IVehiculoRepositorio;
import com.ceiba.parking.dominio.servicio.ServicioParqueadero;
import com.ceiba.parking.infraestructura.persistencia.repositorio.TipoVehiculoRepositorio;
import com.ceiba.parking.infraestructura.persistencia.repositorio.VehiculoRepositorio;

public class ParqueaderoTest {
	
	private static final long IDVEHICULO = 1;
	private static final TipoVehiculo TIPOVEHICULO = new TipoVehiculoDataBuilder().build();
	private static final int CILINDRAJE = 1100;
	private static final String PLACA = "FBH343";
	private static final String DESCRIPCION = "Camioneta";
	private static final int LIMITECUPOSCARROS = 20;
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	private static final String VEHICULONOREGISTRADO = "El vehiculo ingresado no se encuentra registrado";
	
	VehiculoTestDataBuilder vehiculoTestBuilder;
	
	@Autowired
	@Mock
	VehiculoRepositorio vehiculoRepositorio2;
	
	@Autowired
	@Mock
	IVehiculoRepositorio vehiculoRepositorio;
	
	@Mock
	ServicioParqueadero parqueadero;
	
	@Mock
	Vehiculo vehiculo;
	
	@InjectMocks
	private ServicioParqueadero servicioParqueadero;
	
	@Before
	public void setup() {
		vehiculoTestBuilder = new VehiculoTestDataBuilder();
		parqueadero = new ServicioParqueadero();
	}
	
	@Test
	public void crearVehiculo() {
		// arrange
		 VehiculoTestDataBuilder nuevoVehiculoTestBuilder = new VehiculoTestDataBuilder();
		// act
		vehiculo = nuevoVehiculoTestBuilder.build();

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
		ServicioParqueadero newServicioParqueadero = mock(ServicioParqueadero.class);		

		// act
		when(newServicioParqueadero.registroVehiculo(vehiculo)).thenReturn(vehiculo);
		
		// assert
		assertNotNull("Registro éxitoso", newServicioParqueadero.registroVehiculo(vehiculo));
		
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
	
	@Test
	public void obtenerCantidadDeVehiculosPorTipo() {	
		
		// arrange		
		IVehiculoRepositorio nuevoVehiculoRepositorio = mock(IVehiculoRepositorio.class);
		TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder().build();
			
		// act
		when(nuevoVehiculoRepositorio.cantidadVehiculos(tipoVehiculo)).thenReturn(LIMITECUPOSCARROS);
		
		assertEquals(LIMITECUPOSCARROS, nuevoVehiculoRepositorio.cantidadVehiculos(tipoVehiculo));
						
	}
	
	@Test(expected = AccesoDenegado.class)
	public void validarCantidadDeCuposPorTipoDeVehiculoRetornaExcepcion() {	
		
		// arrange		
		IVehiculoRepositorio nuevoVehiculoRepositorio = mock(IVehiculoRepositorio.class);
		TipoVehiculo tipoVehiculo = new TipoVehiculoDataBuilder().build();
					
		// act
		when(nuevoVehiculoRepositorio.cantidadVehiculos(tipoVehiculo)).thenReturn(LIMITECUPOSCARROS);
		int cantidadVehiculosPorTipo = nuevoVehiculoRepositorio.cantidadVehiculos(tipoVehiculo);
		
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
	
	@Test
	public void validarTarifaParaTipoVehiculoCarro8Horas() {	
		// arrange
		int horas = 8;
		float valorTotal = 8000.0f;
		
		ServicioParqueadero newServicioParqueadero = spy(ServicioParqueadero.class);
		vehiculo = new VehiculoTestDataBuilder().build();
		Date fechaActual = parqueadero.obtenerfechaAtual(); 
		
		// act
		when(newServicioParqueadero.calcularTiempoDeParqueo(fechaActual, fechaActual)).thenReturn(horas);
		float valorCalculado = newServicioParqueadero.calcularValorTotal(vehiculo, horas);
		
		//assert
		assertEquals(valorTotal, valorCalculado, 2);
	}
	
	@Test
	public void validarTarifaParaTipoVehiculoCarroMayorA8Horas() {	
		// arrange
		int horas = 25;
		float valorTotal = 9000.0f;
		
		ServicioParqueadero newServicioParqueadero = spy(ServicioParqueadero.class);
		vehiculo = new VehiculoTestDataBuilder().build();
		Date fechaActual = newServicioParqueadero.obtenerfechaAtual(); 
		
		// act
		when(newServicioParqueadero.calcularTiempoDeParqueo(fechaActual, fechaActual)).thenReturn(horas);
		float valorCalculado = newServicioParqueadero.calcularValorTotal(vehiculo, horas);
		
		//assert
		assertEquals(valorTotal, valorCalculado, 2);
	}
	
	@Test
	public void validarTarifaParaTipoVehiculoMoto2HorasCilindrajeMAyorA500cc() {	
		// arrange
		int horas = 2;
		float valorTotal = 3000.0f;
		final int cilindraje = 600;
		
		ServicioParqueadero newServicioParqueadero = spy(ServicioParqueadero.class);
		TipoVehiculoDataBuilder dataBuilder = new TipoVehiculoDataBuilder(1, MOTO);
		TipoVehiculo tipoVehiculo = dataBuilder.build();
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder(1, tipoVehiculo, cilindraje, PLACA);
		vehiculo = vehiculoTestDataBuilder.build();
		Date fechaActual = newServicioParqueadero.obtenerfechaAtual(); 
		
		// act
		when(newServicioParqueadero.calcularTiempoDeParqueo(fechaActual, fechaActual)).thenReturn(horas);
		float valorCalculado = newServicioParqueadero.calcularValorTotal(vehiculo, horas);
		
		//assert
		assertEquals(valorTotal, valorCalculado, 2);
	}
	
	@Test
	public void validarDiaDeRegistroDadaUnaPlacaQueComienzaPorA() {
		// arrange
		String placaCarro = "ABC589";
		String diaDomingo = "DOMINGO";
		parqueadero = spy(ServicioParqueadero.class); 
		
		//act
		when(parqueadero.obtenerDiaActual()).thenReturn(diaDomingo);
		try {
			parqueadero.validarPlaca(placaCarro);
		} catch (Exception e) {
		//assert	
			assertThatExceptionOfType(AccesoDenegado.class);
		}		

	}
	
	@Test
	public void dadoUnVehiculoObtenerLaFacturaYRetoraUnaExcepcion() {
		// arrange
		String placaCarro = "ABC589";
		parqueadero = mock(ServicioParqueadero.class);		
		vehiculo = new VehiculoTestDataBuilder().build();
		
		//act
		when(parqueadero.validarExistenciaPorPlaca(placaCarro)).thenReturn(vehiculo);
		vehiculo = parqueadero.validarExistenciaPorPlaca(placaCarro);
		try {
			parqueadero.obtenerFacturaVehiculo(vehiculo);
		} catch (Exception exception) {
		//assert	
			assertEquals(VEHICULONOREGISTRADO, exception.getMessage());
		}		

	}
	
	@Test
	public void dadoUnVehiculoValidarSiEstaActivo() {
		// arrange		
		vehiculo = new VehiculoTestDataBuilder().build();
		
		IFacturaRepositorio facturaRepositorio = mock(IFacturaRepositorio.class);		
		parqueadero = mock(ServicioParqueadero.class);
		
		//act
		when(facturaRepositorio.obtenerFacturaActiva(vehiculo)).thenReturn(null);
				
		try {
			parqueadero.validarVehiculoActivo(vehiculo);
		} catch (Exception exception) {
		//assert	
			assertEquals(VEHICULONOREGISTRADO, exception.getMessage());
		}		

	}
	
	@Test
	public void validarCantidadDeCuposPorTipoDeVehiculoMoto() {	
		
		// arrange	
		String mensajeError = "No hay mas cupos para el tipo de vehiculo";
		vehiculoRepositorio = mock(IVehiculoRepositorio.class);
		TipoVehiculoDataBuilder tipoVehiculoDataBuilder = new TipoVehiculoDataBuilder(2, MOTO);
		TipoVehiculo tipoVehiculo = tipoVehiculoDataBuilder.build();			
		// act
		when(vehiculoRepositorio.cantidadVehiculos(tipoVehiculo)).thenReturn(LIMITECUPOSCARROS);
		int cantidadVehiculosPorTipo = vehiculoRepositorio.cantidadVehiculos(tipoVehiculo);
		try {
			parqueadero.validarCupo(cantidadVehiculosPorTipo, 10);
		} catch (Exception exception) {
			assertEquals(mensajeError, exception.getMessage());
		}						
	}
	
	@Test
	public void validarCantidadDeCuposPorTipoDeVehiculoNoRegistradoRetornaExcepcion() {	
		
		// arrange	
		String mensajeError = "Datos incorrectos";
		String camion = "Camion";
		vehiculoRepositorio = mock(IVehiculoRepositorio.class);
		TipoVehiculoDataBuilder tipoVehiculoDataBuilder = new TipoVehiculoDataBuilder(3, camion);
		TipoVehiculo tipoVehiculo = tipoVehiculoDataBuilder.build();
		ServicioParqueadero nuevoServicioParqueadero = new ServicioParqueadero();
		// act
		when(vehiculoRepositorio.cantidadVehiculos(tipoVehiculo)).thenReturn(0);
		int cantidadVehiculosPorTipo = vehiculoRepositorio.cantidadVehiculos(tipoVehiculo);
		try {
			nuevoServicioParqueadero.validarCupo(cantidadVehiculosPorTipo, 10);
		} catch (Exception exception) {
			assertEquals(mensajeError, exception.getMessage());
		}						
	}

}
