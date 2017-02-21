package com.isa.bartender;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;
import com.isa.work.day.WorkDay;
import com.isa.work.shift.WorkShift;

@RestController
@RequestMapping("/bartenders")
public class BartenderController {
	
	private HttpSession httpSession;
	private final BartenderService bartenderService;
	private final RestaurantService restaurantService;
	

	@Autowired
	public BartenderController( HttpSession httpSession, final BartenderService bartenderService, final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.bartenderService = bartenderService;
		this.restaurantService = restaurantService;
		
	}
	
	
	@GetMapping(path = "/getBartender")
	public Bartender getBartender() {		
		Bartender bartender = (Bartender) httpSession.getAttribute("user");
		return bartender;
	}
	
	@PutMapping(path = "/updateInfo")
	public Bartender updateBartender(@RequestBody Bartender bartender) {				
		
		try{
			bartenderService.save(bartender);
			httpSession.setAttribute("user", bartender);
		} catch(Exception e) {
			System.out.println("Greska pri update-u sankera");
			return null;
		}
		
		return bartender;	
	}
	
	@PutMapping(path = "/changePassword")
	public Bartender changeBartenderPassword(@RequestBody Bartender bartender) {
		
		if(bartender.getFirstLogIn())
			bartender.setFirstLogIn(false);
		
		try{
			bartenderService.save(bartender);
			httpSession.setAttribute("user", bartender);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre sankera");
			return null;
		}
		
		return bartender;	
	}
	

	@GetMapping(path = "/getSchedule/{id}")
	public List<WorkDay> getSchedule(@PathVariable Long id) {
				
		Bartender bartender= bartenderService.findOne(id);
		Long restaurantId = bartender.getRestaurantId();		
		Restaurant restaurant = restaurantService.findOne(restaurantId);
			
		List<WorkDay> bartenderDays= new ArrayList<WorkDay>();
		
		for(WorkDay day : restaurant.getWorkDays()) {						
			for(WorkShift shift : day.getWorkShifts()) {
				if(shift.getBartenders().size() != 0)
					bartenderDays.add(day);
			}			
		}
				
		return bartenderDays;		
	}
	
	
}
