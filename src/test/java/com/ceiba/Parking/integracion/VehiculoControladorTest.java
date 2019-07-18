package com.ceiba.Parking.integracion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.Parking.ParkingApplication;
import com.ceiba.Parking.databuilder.FacturaTestDataBuilder;
import com.ceiba.Parking.databuilder.VehiculoTestDataBuilder;
import com.ceiba.Parking.dominio.modelo.Factura;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.ceiba.Parking.dominio.servicio.ServicioParqueadero;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingApplication.class)
@AutoConfigureMockMvc
public class VehiculoControladorTest {
	
	private static final String PLACA = "FBH343";
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void registroVehiculoTest() throws Exception {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		mockMvc.perform(post("/parqueadero")
				.content(new ObjectMapper().writeValueAsString(vehiculo))
				.contentType(MediaType.APPLICATION_JSON))				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.placa").value(PLACA));
		
	}
	
	@Test
	public void obtenerVehiculosActivosTest() throws Exception {
		
		mockMvc.perform(get("/parqueadero/vehiculos/activos")
				.contentType(MediaType.APPLICATION_JSON))				
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void generarFacturaVehiculo() throws Exception {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();		
		registroVehiculoTest();
		
		mockMvc.perform(post("/parqueadero/vehiculo/factura")
				.content(new ObjectMapper().writeValueAsString(vehiculo))
				.contentType(MediaType.APPLICATION_JSON))				
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.vehiculo.placa").value(PLACA));
		
	}

}
