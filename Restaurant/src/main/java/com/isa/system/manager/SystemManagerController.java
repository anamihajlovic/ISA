package com.isa.system.manager;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.isa.res.manager.*;
import com.isa.user.Role;

@RestController
@RequestMapping("/sysManager")
public class SystemManagerController {
	private final RestaurantManagerService restaurantManagerService;
	
	@Autowired
	public SystemManagerController(RestaurantManagerService restaurantManagerService) {
		this.restaurantManagerService = restaurantManagerService;

	}
	
	
	@PostMapping(path = "/newResManager")
	public RestaurantManager saveResManager(@RequestBody RestaurantManager restaurantManager) {
		System.out.println("uslo");
		if (restaurantManager != null){
		restaurantManager.setId(null);
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




}
