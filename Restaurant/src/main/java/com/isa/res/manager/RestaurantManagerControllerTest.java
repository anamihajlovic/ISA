package com.isa.res.manager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.isa.bartender.Bartender;

import com.isa.bidder.Bidder;

import com.isa.cook.Cook;
import com.isa.cook.CookType;
import com.isa.dish.Dish;
import com.isa.dish.DishRepository;
import com.isa.dish.DishType;
import com.isa.drink.Drink;

import com.isa.drink.DrinkType;
import com.isa.employed.ClothesSize;
import com.isa.employed.ShoesSize;
import com.isa.foodstuf.Foodstuff;
import com.isa.res.manager.RestaurantManager;
import com.isa.res.manager.RestaurantManagerRepository;

import com.isa.res.order.ResOrder;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantRepository;

import com.isa.user.Role;
import com.isa.waiter.Waiter;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantManagerControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	
	@Autowired
	private RestaurantManagerRepository restaurantManagerRepository;
	
	
	@Autowired
	private DishRepository dishRepository;
	
	
	@Autowired
	private RestaurantRepository resRepository;
	
	protected MockHttpSession session;
	
	private MockMvc mvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
		Restaurant res1 = new Restaurant();		
		res1.setId(Long.parseLong("1"));
		res1.setName("Ime");
		res1.setCountry("Srbija");
		res1.setCity("Novi Sad");
		res1.setStreet("Zeleznicka");
		res1.setNumber(5);
		res1.setRestaurant_type("kineski");
		resRepository.save(res1);
		
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		restaurantManagerRepository.save(rm);
		
		Dish dish1 = new Dish();
		dish1.setId(5);
		dish1.setName("supa");
		dish1.setText("opis");
		dish1.setPrice(Long.parseLong("200"));
		dish1.setDishType(DishType.soup);
		
		Dish dish2 = new Dish();
		dish2.setId(4);
		dish2.setName("testo");
		dish2.setText("opis");
		dish2.setPrice(Long.parseLong("500"));
		dish2.setDishType(DishType.pasta);
		
		dishRepository.save(dish1);
		dishRepository.save(dish1);
		List<Dish> dishes= new ArrayList<Dish>();
		dishes.add(dish1);
		dishes.add(dish2);
		resRepository.findOne(Long.parseLong("1")).setDishes(dishes);
		
		
	}
	
	@Test
	public void testRegistWaiter() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Waiter w = new Waiter();
		w.setId(Long.parseLong("1"));
		w.setEmail("wa1@gmail.com");
		w.setFirstName("waiter1");
		w.setLastName("waiter1");
		w.setPassword("wa1");
		w.setUserRole(Role.waiter);
		w.setFirstLogIn(true);
		Date d = new Date();
		w.setBirthday(d);
		w.setClothesSize(ClothesSize.L);
		w.setShoesSize(ShoesSize.no38);
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newWaiter")	
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(w))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testRegistCook() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Cook c = new Cook();
		c.setId(Long.parseLong("1"));
		c.setEmail("cook1@gmail.com");
		c.setFirstName("cook1");
		c.setLastName("cook1");
		c.setPassword("cook1");
		c.setFirstLogIn(true);
		c.setUserRole(Role.cook);
		c.setCookType(CookType.appetizerCook);
		Date d = new Date();
		c.setBirthday(d);
		c.setClothesSize(ClothesSize.L);
		c.setShoesSize(ShoesSize.no38);
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newCook")	
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(c))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testRegistBartender() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Bartender bar = new Bartender();
		bar.setId(Long.parseLong("1"));
		bar.setEmail("bar1@gmail.com");
		bar.setFirstName("bar1");
		bar.setLastName("bar1");
		bar.setPassword("bar1");
		bar.setFirstLogIn(true);
		bar.setUserRole(Role.bartender);
		Date d = new Date();
		bar.setBirthday(d);
		bar.setClothesSize(ClothesSize.L);
		bar.setShoesSize(ShoesSize.no38);
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newBartender")	
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(bar))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testRegistBidder() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Bidder bid = new Bidder();
		bid.setId(Long.parseLong("1"));
		bid.setEmail("bid1@gmail.com");
		bid.setFirstName("bid1");
		bid.setLastName("bid1");
		bid.setPassword("bid1");

		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newBidder")
											.contentType(MediaType.APPLICATION_JSON)
								            .content(objectMapper.writeValueAsBytes(bid))
								            .sessionAttrs(sessionattr))
								            .andExpect(MockMvcResultMatchers.status().isOk());
				
			
	}
	
	@Test
	public void testNewDish() throws Exception {
		
		
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Dish d = new Dish();
		d.setId(1);
		d.setName("hleb");
		d.setText("opis");
		d.setPrice(Long.parseLong("100"));
		d.setDishType(DishType.breakfast);
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newDish")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(d))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testNewDrink() throws Exception {
				
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Drink d = new Drink();
		d.setId(1);
		d.setName("kafa");
		d.setText("opis");
		d.setPrice(Long.parseLong("100"));
		d.setDrinkType(DrinkType.coffee);
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newDrink")	
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(d))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testNewFoodstuff() throws Exception {
				
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Foodstuff f = new Foodstuff();
		f.setId(1);
		f.setName("banana");
		f.setQuantity(5);

		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.post("/resManager/newFoodstuff")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(f))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testNewResOrder() throws Exception {
				
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		ResOrder ro = new ResOrder();
		ro.setId(Long.parseLong("1"));
		ro.setResName("Ime");
		ro.setEndDate("2017-07-03");
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.put("/resManager/finalSaveResOrder")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(ro))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetRestaurants() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.get("/resManager/restaurant").sessionAttrs(sessionattr))
    			.andExpect(MockMvcResultMatchers.status().isOk());
				   
	}
	
	@Test
	public void testUpdateResManager() throws Exception {
				
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		RestaurantManager ulogovan = new RestaurantManager();
		ulogovan.setId(Long.parseLong("1"));
		ulogovan.setEmail("res1@gmail.com");
		ulogovan.setPassword("resman1");
		ulogovan.setFirstName("promena");
		ulogovan.setLastName("promena");	
		ulogovan.setUserRole(Role.resManager);
		ulogovan.setFirstLogIn(false);	
		ulogovan.setIdRestaurant(Long.parseLong("1"));

		long id = 1;
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.put("/resManager/"+id)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(ulogovan))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testUpdateRestaurant() throws Exception {
				
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		Restaurant promena = new Restaurant();		
		promena.setId(Long.parseLong("1"));
		promena.setName("Novi Naziv");
		promena.setCountry("Srbija");
		promena.setCity("Novi Sad");
		promena.setStreet("Zeleznicka");
		promena.setNumber(5);
		promena.setRestaurant_type("novi tip");

		long id = 1;
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.put("/resManager/update/"+id)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsBytes(promena))
	            .sessionAttrs(sessionattr))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void testGetDishes() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		mvc.perform(MockMvcRequestBuilders.get("/resManager/dishes").sessionAttrs(sessionattr))
    			.andExpect(MockMvcResultMatchers.status().isOk());
				   
	}
	
	@Test
	public void testDeleteDish() throws Exception {
		RestaurantManager rm = new RestaurantManager();
		rm.setId(Long.parseLong("1"));
		rm.setEmail("res1@gmail.com");
		rm.setPassword("resman1");
		rm.setFirstName("resFirst");
		rm.setLastName("resLast");	
		rm.setUserRole(Role.resManager);
		rm.setFirstLogIn(false);	
		rm.setIdRestaurant(Long.parseLong("1"));
		
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("user", rm);
		int id = 5;
		mvc.perform(MockMvcRequestBuilders.delete("/resManager/deleteDish/"+id).sessionAttrs(sessionattr))
    			.andExpect(MockMvcResultMatchers.status().isOk());
				   
	}
	

}
