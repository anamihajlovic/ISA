package com.isa.invitation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvitationTests {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	

	@Test
	public void testSendInvitation() throws Exception {
		Long reservationId = 2L;
		Long friendId = 5L;
		
		this.mvc.perform(post("/invitations/sendInvitation/" + friendId).contentType(MediaType.APPLICATION_JSON).content(asJsonString(reservationId)))
		.andExpect(status().isOk()).andExpect(content().string("OK"));
	}
	
	@Test
	public void testConfirmInvitation() throws Exception {
		Long invitationId = 5L;
		String operation = "accept";
		
		this.mvc.perform(post("/invitations/confirmInvitation/" + invitationId + "/" + operation))
		.andExpect(status().isOk());
	}
	

	@Test
	public void testGetInvitedFriends() throws Exception {
		
		Long reservationId = 1L;
		Long guestId = 1L;
		
		this.mvc.perform(get("/invitations/getInvitedFriends/" + reservationId + "/" + guestId)).andExpect(status().isOk());

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
