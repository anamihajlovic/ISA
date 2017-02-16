package com.isa.system.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.isa.res.manager.*;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;
import com.isa.user.Role;


@RestController
@RequestMapping("/sysManager")
public class SystemManagerController {
	private final RestaurantManagerService restaurantManagerService;
	private final RestaurantService restaurantService;
	
	@Autowired
	public SystemManagerController(final RestaurantManagerService restaurantManagerService,
			 final RestaurantService restaurantService) {
		this.restaurantManagerService = restaurantManagerService;
		this.restaurantService= restaurantService;

	}
	
	
	@PostMapping(path = "/newResManager")
	public RestaurantManager saveResManager(@RequestBody RestaurantManager restaurantManager) {
		//System.out.println("uslo");
		if (restaurantManager != null){
		//restaurantManager.setId(null);//mozda ovo ne bi trebalo dirati jer ovo baza sama regulise
		restaurantManager.setFirstLogIn(true);
		restaurantManager.setUserRole(Role.resManager);
		restaurantManagerService.save(restaurantManager);
		System.out.println(restaurantManager.getFirstName()+" "+restaurantManager.getLastName()+
				restaurantManager.getEmail()+" "+restaurantManager.getPassword()+" "+
				restaurantManager.getFirstLogIn()+" "+restaurantManager.getUserRole()+" "+
				restaurantManager.getId()
		
		
				
				);
		return restaurantManager;
	}
		else
			return null;
		
	}
	
	@GetMapping(path = "/freeResManager")
	public List<RestaurantManager> findAllFreeRestaurantManagers() {
		System.out.println("uslo");
		
		
		List<Restaurant> restaurants = restaurantService.findAll();
		System.out.println("i ovde");
		System.out.println(restaurants.isEmpty());
		// ovo ce se kasnije promeniti da ide odmah na bazu, sa posebnim upitom
	    List<RestaurantManager> managers = restaurantManagerService.findAll();
	    System.out.println("iiii ovde");
		System.out.println(managers.isEmpty());
		List<RestaurantManager> result = new ArrayList<RestaurantManager>();
		for (RestaurantManager m : managers){
			for (Restaurant r : restaurants){
				for (RestaurantManager rr : r.getRestaurantManagers()){
					if (m.getId()!=rr.getId()){
						result.add(m);
						System.out.println(m.getId());
					}
					
					}
			}
		}
	
		return result;
	}
	@PostMapping(path = "/newRestaurant")
	public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
		System.out.println("uslo u restorane");
		if (restaurant != null){
			restaurant.setRatings(0.0);	
			restaurantService.save(restaurant);
		return restaurant;
	}
		else
			return null;
		
	}
	



}
