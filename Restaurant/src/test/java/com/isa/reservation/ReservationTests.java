package com.isa.reservation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTests {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	
	@Test
	public void testGetReservation() throws Exception {

		Long id = 3L;
		
		this.mvc.perform(get("/reservations/getReservation/" + id)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void testGetMyVisits() throws Exception {
		
		Long guestId = 1L;
		
		this.mvc.perform(get("/reservations/getMyVisits/" + guestId)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetActiveReservations() throws Exception {
		Long guestId = 2L;
				
		this.mvc.perform(get("/reservations/getActiveReservations/" + guestId)).andExpect(status().isOk());


	}
	

	@Test
	public void testDeleteReservation() throws Exception {
		Long id = 7L;
		
		this.mvc.perform(delete("/reservations/deleteReservation/" + id))
		.andExpect(status().isOk()).andExpect(content().string("OK"));
	}

}
