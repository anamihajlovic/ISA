package com.isa.cook;

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
@RequestMapping("/cooks")
public class CookController {
	
	private HttpSession httpSession;
	private final CookService cookService;
	private final RestaurantService restaurantService;
	
	@Autowired
	public CookController(final HttpSession httpSession, final CookService cookService, final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.cookService = cookService;
		this.restaurantService = restaurantService;
	}
	
	@PutMapping(path = "/updateInfo")
	public Cook updateCook(@RequestBody Cook cook) {		
		
		try{
			cookService.save(cook);
			httpSession.setAttribute("user", cook);
		} catch(Exception e) {
			System.out.println("Greska pri update-u kuvara.");
			return null;
		}
		
		return cook;				
	}
	
	@PutMapping(path = "/changePassword")
	public Cook changeCookPassword(@RequestBody Cook cook) {
		
		if(cook.getFirstLogIn())
			cook.setFirstLogIn(false);
		
		try{
			cookService.save(cook);
			httpSession.setAttribute("user", cook);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre kuvara");
			return null;
		}
		
		return cook;	
	}
	
	@GetMapping(path = "/getSchedule/{id}")
	public List<WorkShift> getSchedule(@PathVariable Long id) {
		
		Cook cook = cookService.findOne(id);
		Long restaurantId = cook.getRestaurantId();		
		Restaurant restaurant = restaurantService.findOne(restaurantId);
							
		List<WorkShift> cookShifts= new ArrayList<WorkShift>();
		
		for(WorkDay day : restaurant.getWorkDays()) {
			
			for(WorkShift shift : day.getWorkShifts()) {
				if(shift.getCooks().size() != 0)
					cookShifts.add(shift);
			}
		
		}
		
		return cookShifts;		
	}

}
