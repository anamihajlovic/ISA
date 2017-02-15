package com.isa.system.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isa.res.manager.*;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;
import com.isa.user.Role;

@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {
	
	private final HttpSession httpSession;
	private final SystemManagerService systemManagerService;
	private final RestaurantManagerService restaurantManagerService;
	private final RestaurantService restaurantService;
	
	@Autowired
	public SystemManagerController(HttpSession httpSession, SystemManagerService systemManagerService,RestaurantManagerService restaurantManagerService, RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.systemManagerService = systemManagerService;
		this.restaurantManagerService = restaurantManagerService;
		this.restaurantService = restaurantService;
	}
	
	
	@GetMapping(path = "/restaurantManager")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveRestaurantManager(@Valid @RequestBody RestaurantManager restaurantManager) {
		System.out.println("uslo");
		restaurantManager.setId(null);
		restaurantManager.setFirstLogIn(true);
		restaurantManager.setUserRole(Role.resManager);
		restaurantManagerService.save(restaurantManager);
		System.out.println(restaurantManager.getFirstName()+" "+restaurantManager.getLastName()+
				restaurantManager.getEmail()+" "+restaurantManager.getPassword()+" "+
				restaurantManager.getFirstLogIn()+" "+restaurantManager.getUserRole()+" "+
				restaurantManager.getId()
		
		
				
				);
		
	}




}
