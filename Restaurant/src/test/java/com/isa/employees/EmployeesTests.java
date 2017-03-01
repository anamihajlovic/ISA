package com.isa.employees;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.employed.ClothesSize;
import com.isa.employed.ShoesSize;
import com.isa.user.Role;
import com.isa.waiter.Waiter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeesTests {

	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testUpdateWaiterInfo() throws Exception {
		
		Date birthDay = new GregorianCalendar(1994, Calendar.JULY, 07).getTime();
		
		Waiter waiter = new Waiter();
		waiter.setId(2L);
		waiter.setEmail("waiter2@gmail.com");
		waiter.setPassword("waiter2");
		waiter.setFirstLogIn(true);
		waiter.setFirstName("Konobar");
		waiter.setLastName("Konobaric");
		waiter.setUserRole(Role.waiter);		
		waiter.setRestaurantId(2L);
		waiter.setBirthday(birthDay);
		waiter.setClothesSize(ClothesSize.M);
		waiter.setShoesSize(ShoesSize.no39);
		
		
		this.mvc.perform(put("/waiters/updateInfo").contentType(MediaType.APPLICATION_JSON).content(asJsonString(waiter)))
		.andExpect(status().isOk()).andExpect(content().json(asJsonString(waiter)));
	}
	
	
	@Test
	public void testChangeWaiterPassword() throws Exception {
		
		Date birthDay = new GregorianCalendar(1994, Calendar.JULY, 07).getTime();		

		Waiter waiter = new Waiter();
		waiter.setId(2L);
		waiter.setEmail("waiter2@gmail.com");
		waiter.setPassword("novaSifra");
		waiter.setFirstLogIn(false);
		waiter.setFirstName("waiter2");
		waiter.setLastName("waiter2");
		waiter.setUserRole(Role.waiter);		
		waiter.setRestaurantId(2L);
		waiter.setBirthday(birthDay);
		waiter.setClothesSize(ClothesSize.M);
		waiter.setShoesSize(ShoesSize.no40);
		
		this.mvc.perform(put("/waiters/changePassword").contentType(MediaType.APPLICATION_JSON).content(asJsonString(waiter)))
		.andExpect(status().isOk()).andExpect(content().json(asJsonString(waiter)));
		
	}
	
	@Test
	public void testGetWaiterSchedule() throws Exception {
		
		Long waiterId = 1L;
		this.mvc.perform(get("/waiters/getSchedule/" + waiterId)).andExpect(status().isOk());
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	
}
