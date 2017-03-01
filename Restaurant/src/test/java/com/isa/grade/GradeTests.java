package com.isa.grade;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeTests {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	
	@Test
	public void testNewGrade() throws Exception {
		Date day = new GregorianCalendar(2017, Calendar.MARCH, 01).getTime();		
		Grade newGrade = new Grade(6L, 4L, 2L, 3L, 2L, 4L, 3D, 4D, 3D, day);
		
		this.mvc.perform(post("/grades/addNew").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newGrade)))
		.andExpect(status().isOk());		
	}
	
	@Test
	public void testGetRatedReservation() throws Exception {
		
		Long reservationId = 3L;
		Long guestId = 4L;
		
		this.mvc.perform(get("/grades/getRatedReservation/" + reservationId + "/" +  guestId)).andExpect(status().isOk());
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