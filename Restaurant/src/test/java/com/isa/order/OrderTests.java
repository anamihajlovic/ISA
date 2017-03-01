package com.isa.order;

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
import com.isa.bill.Bill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTests {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testAcceptOrder() throws Exception {
		
		Long orderId = 4L;
		String tableId = "2";
		
		this.mvc.perform(put("/orders/acceptOrder/"+orderId+"/"+tableId)).andExpect(status().isOk());				
	}
	
	@Test
	public void testFinishOrder() throws Exception {
		Long orderId = 3L;
		this.mvc.perform(put("/orders/finishOrder/"+orderId)).andExpect(status().isOk()).andExpect(content().string("success"));
	}
	
	@Test
	public void testGetRestaurantDishOrders() throws Exception {
		Long restaurantId = 1L;
		this.mvc.perform(get("/orders/getRestaurantDishOrders/"+restaurantId)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetRestaurantDrinkOrders() throws Exception {
		Long restaurantId = 1L;
		this.mvc.perform(get("/orders/getRestaurantDrinkOrders/"+restaurantId)).andExpect(status().isOk());
	}
	
	@Test
	public void testCreateBill() throws Exception {		
		
		Bill bill = new Bill();
		bill.setId(38L);
		bill.setBillDate("2017-02-27");
		bill.setTime("12:10:01");
		bill.setTotalPrice(4000L);
		bill.setWaiterId(2L);
		
		this.mvc.perform(post("/bills/createBill").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bill)))
		.andExpect(status().isOk()).andExpect(content().string("success"));

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
