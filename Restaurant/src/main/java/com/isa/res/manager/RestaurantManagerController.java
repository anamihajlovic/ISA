package com.isa.res.manager;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bidder.*;
import com.isa.cook.Cook;
import com.isa.dish.Dish;
import com.isa.drink.Drink;
import com.isa.res.manager.*;
import com.isa.restaurant.*;




@RestController
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {
	
	private final HttpSession httpSession;
	private final RestaurantService restaurantService;
	private final RestaurantManagerService restaurantManagerService;
	
	@Autowired
	public RestaurantManagerController(HttpSession httpSession, RestaurantService restaurantService,
			RestaurantManagerService restaurantManagerService) {
		super();
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.restaurantManagerService = restaurantManagerService;
	}

		

		

		
		

		
	
	
	
	

}