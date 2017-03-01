package com.isa.system.manager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.res.manager.RestaurantManager;
import com.isa.res.manager.RestaurantManagerRepository;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantRepository;
import com.isa.user.Role;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private SystemManagerRepository systemManagerRepository;
	
	@Autowired
	private RestaurantManagerRepository resManagerRepository;
	
	@Autowired
	private RestaurantRepository resRepository;
	
	protected MockHttpSession session;
	
	private MockMvc mvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
		/*SystemManager sysman1 = new SystemManager();
		sysman1.setId(Long.parseLong("1"));
		sysman1.setEmail("sysman1@gmail.com");
		sysman1.setPassword("sysman1");
		sysman1.setFirstName("sysFirst");
		sysman1.setLastName("sysLast");
		sysman1.setUserRole(Role.sysManager);
		sysman1.setPreset(Preset.yes);
		sysman1.setFirstLogIn(false);		
		systemManagerRepository.save(sysman1);
		
		Restaurant res1 = new Restaurant();		
		res1.setId(Long.parseLong("1"));
		res1.setName("Ime");
		res1.setCountry("Srbija");
		res1.setCity("Novi Sad");
		res1.setStreet("Zeleznicka");
		res1.setNumber(5);
		res1.setRestaurant_type("kineski");
		resRepository.save(res1);*/
	}
	
	@Test
	public void testRegistSysMan() throws Exception {
		SystemManager sysman2 = new SystemManager();
		sysman2.setId(Long.parseLong("2"));
		sysman2.setEmail("sysman2@gmail.com");
		sysman2.setPassword("sysman2");
		sysman2.setFirstName("sysFirst");
		sysman2.setLastName("sysLast");
		
		mvc.perform(post("/sysManager/newSysManager")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(sysman2)))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testRegistRestaurant() throws Exception {
		Restaurant res = new Restaurant();
		
		res.setId(Long.parseLong("2"));
		res.setName("Neko");
		res.setCountry("Srbija");
		res.setCity("Beograd");
		res.setStreet("Zeleznicka");
		res.setNumber(10);	
		res.setRestaurant_type("srpski");
		
		mvc.perform(post("/sysManager/newRestaurant")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(res)))
	            .andExpect(status().isOk());
		
		
		
	}
	
	@Test
	public void testDeleteResManager() throws Exception {			
		mvc.perform(delete("/sysManager/deleteResMen/"+Long.parseLong("1")))
	    	.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteRestaurant() throws Exception {		
		mvc.perform(delete("/sysManager/deleteRestaurant/"+Long.parseLong("1")))
	    	.andExpect(status().isOk());
	}
	
	@Test
	public void testGetRestaurants() throws Exception {
		mvc.perform(get("/sysManager/Restaurants"))
			   .andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
		        .andExpect(jsonPath("$[0].name", Matchers.is("Ime")))
		        .andExpect(jsonPath("$[0].country", Matchers.is("Srbija")));
				   
	}
	
	
	
	

}
