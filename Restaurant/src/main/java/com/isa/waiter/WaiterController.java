package com.isa.waiter;

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
@RequestMapping("/waiters")
public class WaiterController {
	
	private HttpSession httpSession;
	private final WaiterService waiterService;
	private final RestaurantService restaurantService;
	
	@Autowired
	public WaiterController(final HttpSession httpSession, final WaiterService waiterService, final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.waiterService = waiterService;
		this.restaurantService = restaurantService;
	}
	
	@PutMapping(path = "/updateInfo")
	public Waiter updateWaiter(@RequestBody Waiter waiter) {		
		try{
			waiterService.save(waiter);
			httpSession.setAttribute("user", waiter);
		} catch(Exception e) {
			System.out.println("Greska pri update-u konobara.");
			return null;
		}
		
		return waiter;					
	}
	
	@PutMapping(path = "/changePassword")
	public Waiter changeWaiterPassword(@RequestBody Waiter waiter) {
		
		if(waiter.getFirstLogIn())
			waiter.setFirstLogIn(false);
		
		try{
			waiterService.save(waiter);
			httpSession.setAttribute("user", waiter);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre konobara");
			return null;
		}
		
		return waiter;	
	}
	
	@GetMapping(path = "/getSchedule/{id}")
	public List<WorkShift> getSchedule(@PathVariable Long id) {
		
		Waiter waiter= waiterService.findOne(id);
		Long restaurantId = waiter.getRestaurantId();		
		Restaurant restaurant = restaurantService.findOne(restaurantId);
							
		List<WorkShift> waiterShifts= new ArrayList<WorkShift>();
		
		for(WorkDay day : restaurant.getWorkDays()) {
			
			for(WorkShift shift : day.getWorkShifts()) {
				if(shift.getWaiters().size() != 0)
					waiterShifts.add(shift);
			}
		
		}
		
		return waiterShifts;		
	}

}
