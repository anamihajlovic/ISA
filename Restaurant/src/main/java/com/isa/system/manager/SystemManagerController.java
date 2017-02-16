package com.isa.system.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.isa.res.manager.*;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantPojo;
import com.isa.restaurant.RestaurantService;
import com.isa.user.Role;


@RestController
@RequestMapping("/sysManager")
public class SystemManagerController {
	private HttpSession session;
	private final RestaurantManagerService restaurantManagerService;
	private final RestaurantService restaurantService;
	private final SystemManagerService systemManagerService;
	
	@Autowired
	public SystemManagerController(final RestaurantManagerService restaurantManagerService,
			 final RestaurantService restaurantService, HttpSession session,
			 final SystemManagerService systemManagerService
			 ) {
		this.restaurantManagerService = restaurantManagerService;
		this.restaurantService= restaurantService;
		this.session = session;
		this.systemManagerService= systemManagerService;

	}

	@GetMapping("/checkRights")
	public SystemManager checkRights() {
		
			SystemManager systemManager =  (SystemManager) session.getAttribute("user");

				return systemManager;
			
	}
	
	
	@PostMapping(path = "/newResManager")
	public RestaurantManager saveResManager(@RequestBody RestaurantManager restaurantManager) {
		//System.out.println("uslo");
		if (restaurantManager != null){
		//restaurantManager.setId(null);//mozda ovo ne bi trebalo dirati jer ovo baza sama regulise
		restaurantManager.setFirstLogIn(true);
		restaurantManager.setUserRole(Role.resManager);
		//restaurantManager.setActive(false);
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
		//System.out.println("uslo");
		List<Restaurant> restaurants = restaurantService.findAll();
		//System.out.println("i ovde");
		//System.out.println(restaurants.isEmpty());
		// ovo ce se kasnije promeniti da ide odmah na bazu, sa posebnim upitom
	    List<RestaurantManager> managers = restaurantManagerService.findAll();
	   // System.out.println("iiii ovde");
		//System.out.println(managers.isEmpty());
		List<RestaurantManager> result = new ArrayList<RestaurantManager>();
		List<Long>skup = new ArrayList<Long>();
		for (Restaurant r : restaurants){
			for (RestaurantManager rr : r.getRestaurantManagers()){
				
					skup.add(rr.getId());				
			}
		}
		//System.out.println("skup "+skup);
		for(RestaurantManager m :managers){
			if(!skup.contains(m.getId()))
			{
				result.add(m);
			}
		}
		
	
		return result;
	}
	
	@GetMapping(path = "/ResManagers")
	public List<RestaurantManager> findAllRestaurantManagers() {
	    List<RestaurantManager> managers = restaurantManagerService.findAll();
		return managers;
	}
	
	@GetMapping(path = "/Restaurants")
	public List<Restaurant> findAllRestaurants() {
	    List<Restaurant> restaurants =restaurantService.findAll();
		return restaurants;
	}
	
	@PostMapping(path = "/newRestaurant")
	public Restaurant saveRestaurant(@RequestBody RestaurantPojo restaurant) {
		System.out.println("uslo u restorane");
		if (restaurant != null){
			Restaurant r = new Restaurant();
			r.setName(restaurant.getName());
			r.setCity(restaurant.getCity());
			r.setStreet(restaurant.getStreet());
			r.setCountry(restaurant.getCountry());
			r.setRestaurant_type(restaurant.getRestaurant_type());			
			Long id = Long.parseLong(restaurant.getRestaurantManager());
			RestaurantManager newOne = restaurantManagerService.findOne(id);
			List<RestaurantManager> newList = new ArrayList <RestaurantManager>();
			newList.add(newOne);		
			r.setRestaurantManagers(newList);
			r.setRatings(0.0);
			restaurantService.save(r);
		return r;
	}
		else
			return null;
		
	}
	
	@PutMapping(path = "/{id}")
	public SystemManager updateSysManager(@PathVariable Long id,@RequestBody SystemManager systemManager) {
	restaurantManagerService.findOne(id);
			
		systemManager.setId(id);
		return systemManagerService.save(systemManager);
	}

	
	@PostMapping(path = "/newSysManager")
	public SystemManager saveSysManager(@RequestBody SystemManager systemManager) {
		//System.out.println("uslo");
		if (systemManager != null){
		//restaurantManager.setId(null);//mozda ovo ne bi trebalo dirati jer ovo baza sama regulise
			systemManager.setFirstLogIn(true);
			systemManager.setUserRole(Role.sysManager);
			systemManager.setPreset(Preset.no);
		//restaurantManager.setActive(false);
		systemManagerService.save(systemManager);
		return systemManager;
	}
		else
			return null;
		
	}
}
