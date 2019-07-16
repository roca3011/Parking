package com.ceiba.Parking.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.Parking.ParkingApplication;
import com.ceiba.Parking.databuilder.VehiculoTestDataBuilder;
import com.ceiba.Parking.dominio.modelo.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingApplication.class)
@AutoConfigureMockMvc
public class VehiculoControladorTest {
	
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
				.andExpect(status().isCreated());
		
	}

}
