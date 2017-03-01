package com.isa.guest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.user.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestTests {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testRegister() throws Exception {
		RegisterData registerData = new RegisterData("Pera", "Peric", "pera@gmail.com", "pera", "pera");
		
		this.mvc.perform(post("/guests/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(registerData)))
		.andExpect(status().isOk()).andExpect(content().string("OK"));

	}
	
	@Test
	public void testAcountActivation() throws Exception {
		String activationCode = "ljy769zde";
		
		this.mvc.perform(post("/guests/activateAccount/" + activationCode)).andExpect(status().isOk());

	}
	
	@Test
	public void testUpdateProfile() throws Exception {
		
		Guest guest = new Guest();
		guest.setId(8L);
		guest.setEmail("bakir@gmail.com");
		guest.setPassword("baki");
		guest.setFirstName("Baki");//ovo je promenjeno
		guest.setLastName("Niksic");
		guest.setUserRole(Role.guest);
		guest.setFirstLogIn(false);
		guest.setActive(true);
		guest.setActivationCode("law369zde");
		
		this.mvc.perform(put("/guests/updateProfile").contentType(MediaType.APPLICATION_JSON).content(asJsonString(guest)))
		.andExpect(status().isOk()).andExpect(content().json(asJsonString(guest)));

	}
	
	@Test
	public void testChangePassword() throws Exception {
		Guest guest = new Guest();
		guest.setId(8L);
		guest.setEmail("bakir@gmail.com");
		guest.setPassword("baki94");//promenjeno iz baki->baki94
		guest.setFirstName("Baki");
		guest.setLastName("Niksic");
		guest.setUserRole(Role.guest);
		guest.setFirstLogIn(false);
		guest.setActive(true);
		guest.setActivationCode("law369zde");
		
		this.mvc.perform(put("/guests/changePassword").contentType(MediaType.APPLICATION_JSON).content(asJsonString(guest)))
		.andExpect(status().isOk()).andExpect(content().json(asJsonString(guest)));
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
