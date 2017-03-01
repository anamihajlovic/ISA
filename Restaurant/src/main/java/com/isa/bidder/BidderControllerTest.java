package com.isa.bidder;

import java.util.HashMap;

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
import com.isa.user.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidderControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private BidderRepository bidderRepository;
	
	
	protected MockHttpSession session;
	
	private MockMvc mvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
	/*	Bidder bid1 = new Bidder();
		bid1.setId(Long.parseLong("1"));
		bid1.setEmail("bid1@gmail.com");
		bid1.setFirstName("bid1");
		bid1.setLastName("bid1");
		bid1.setPassword("bid1");
		bid1.setFirstLogIn(false);
		bid1.setUserRole(Role.bidder);
		
		Bidder bid2 = new Bidder();
		bid2.setId(Long.parseLong("2"));
		bid2.setEmail("bid2@gmail.com");
		bid2.setFirstName("bid2");
		bid2.setLastName("bid2");
		bid2.setPassword("bid2");
		bid2.setFirstLogIn(true);
		bid2.setUserRole(Role.bidder);
		bidderRepository.save(bid1);
		bidderRepository.save(bid2);
		*/

		
	}
	
	
	
	@Test
	public void testUpdateBidder() throws Exception {
				
		Bidder bid = new Bidder();
		bid.setId(Long.parseLong("1"));
		bid.setEmail("bid1@gmail.com");
		bid.setFirstName("bid1");
		bid.setLastName("bid1");
		bid.setPassword("bid1");
		bid.setFirstLogIn(false);
		bid.setUserRole(Role.bidder);
		
		Bidder ulogovan = new Bidder();
		ulogovan.setId(Long.parseLong("1"));
		ulogovan.setFirstName("promena");
		ulogovan.setLastName("promena");	


		long id = 1;
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", bid);
		mvc.perform(MockMvcRequestBuilders.put("/bidder/"+id)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(ulogovan))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testFirstLogIn() throws Exception {
				
		Bidder bid = new Bidder();
		bid.setId(Long.parseLong("2"));
		bid.setEmail("bid2@gmail.com");
		bid.setFirstName("bid2");
		bid.setLastName("bid2");
		bid.setPassword("bid2");
		bid.setFirstLogIn(true);
		bid.setUserRole(Role.bidder);
		
		Bidder ulogovan = new Bidder();
		ulogovan.setId(Long.parseLong("2"));
		ulogovan.setPassword("promena");
		
		ulogovan.setEmail("bid2@gmail.com");
		ulogovan.setFirstName("bid2");
		ulogovan.setLastName("bid2");
		ulogovan.setFirstLogIn(true);
		ulogovan.setUserRole(Role.bidder);
		
		
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", bid);
		mvc.perform(MockMvcRequestBuilders.put("/bidder/changePassword")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(ulogovan))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	

}
