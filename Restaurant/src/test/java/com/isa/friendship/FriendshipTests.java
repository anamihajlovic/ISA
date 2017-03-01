package com.isa.friendship;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class FriendshipTests {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testFindFriends() throws Exception {

		Long id = 5L;
		
		this.mvc.perform(get("/friendships/findFriends/" + id)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void testMyFriends() throws Exception {
		
		Long id = 2L;
		
		this.mvc.perform(get("/friendships/myFriends/" + id)).andExpect(status().isOk());

	}
	

	
	@Test
	public void testAddFriend() throws Exception {
		Long guestId = 5L;
		Long friendId = 6L;
		
		this.mvc.perform(post("/friendships/addFriend/" + guestId).contentType(MediaType.APPLICATION_JSON).content(asJsonString(friendId)))
		.andExpect(status().isOk()).andExpect(content().string("OK"));
	}
	

	
	@Test
	public void testDeleteFriend() throws Exception {
		Long guestId = 8L;
		Long friendId = 2L;
		
		this.mvc.perform(delete("/friendships/deleteFriend/" + guestId +"/" + friendId))
		.andExpect(status().isOk()).andExpect(content().string("OK"));
	}
	

	@Test
	public void testConfirmFriendRequest() throws Exception {
		
		Long senderId = 7L;
		Long receiverId = 1L;
		
		this.mvc.perform(put("/friendships/confirm/" + senderId + "/" + receiverId))
		.andExpect(status().isOk()).andExpect(content().string("OK"));
	}
	

	
	@Test
	public void testDeleteFriendRequest() throws Exception {
		Long senderId = 6L;
		Long receiverId = 2L;
		
		this.mvc.perform(delete("/friendships/deleteRequest/" + senderId +"/" + receiverId))
		.andExpect(status().isOk()).andExpect(content().string("OK"));
	}
	
	@Test
	public void testDeleteFriendRequestFailure() throws Exception {
		Long senderId = 3L;
		Long receiverId = 7L;
		
		this.mvc.perform(delete("/friendships/deleteRequest/" + senderId +"/" + receiverId))
		.andExpect(status().isOk()).andExpect(content().string("FAILURE"));
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
