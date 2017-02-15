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
		// ovo ce se kasnije promeniti da ide odmah na bazu, sa posebnim upitom
		List<RestaurantManager> managers = restaurantManagerService.findAll();
		List<Restaurant> restaurants = restaurantService.findAll();
		
		List<RestaurantManager> result = new ArrayList<RestaurantManager>();

		return result;
	}




}
