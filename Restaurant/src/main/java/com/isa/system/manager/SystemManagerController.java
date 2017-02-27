package com.isa.system.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private HttpSession session;
	private final RestaurantManagerService restaurantManagerService;
	private final RestaurantService restaurantService;
	private final SystemManagerService systemManagerService;
	private  Long idRestaurant;
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
	public RestaurantManager saveResManager(@Valid @RequestBody RestaurantManager restaurantManager) {
		//System.out.println("uslo");
		if (restaurantManager != null){
		//restaurantManager.setId(null);//mozda ovo ne bi trebalo dirati jer ovo baza sama regulise
		restaurantManager.setFirstLogIn(false);
		restaurantManager.setUserRole(Role.resManager);		
		restaurantManagerService.save(restaurantManager);
		Restaurant r = restaurantService.findOne(restaurantManager.getIdRestaurant());
		r.getRestaurantManagers().add(restaurantManager);
		r.setId(restaurantManager.getIdRestaurant());
		restaurantService.save(r);		
		return restaurantManager;
	}
		else
			return null;	
	}
	
	@GetMapping(path = "/ResManagers/{id}")
	public List<RestaurantManager> findAllRestaurantManagers(@PathVariable Long id) {
		//System.out.println("uslo");
		idRestaurant = id;
	    List<RestaurantManager> managers = restaurantService.findOne(id).getRestaurantManagers();
	    //System.out.println("velicina " +managers.size());
		return managers;
	}
	
	@GetMapping(path = "/Restaurants")
	public List<Restaurant> findAllRestaurants() {
	    List<Restaurant> restaurants =restaurantService.findAll();
		return restaurants;
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
	
	@PutMapping(path = "/{id}")
	public SystemManager updateSysManager(@PathVariable Long id,@RequestBody SystemManager systemManager) {
		try{
			systemManagerService.save(systemManager);
			session.setAttribute("user", systemManager);
		} catch(Exception e) {
			return null;
		}
		return systemManager;
	}

	
	@PostMapping(path = "/newSysManager")
	public SystemManager saveSysManager(@RequestBody SystemManager systemManager) {
		//System.out.println("uslo");
		if (systemManager != null){
		//restaurantManager.setId(null);//mozda ovo ne bi trebalo dirati jer ovo baza sama regulise
			systemManager.setFirstLogIn(false);
			systemManager.setUserRole(Role.sysManager);
			systemManager.setPreset(Preset.no);
		//restaurantManager.setActive(false);
		systemManagerService.save(systemManager);
		return systemManager;
	}
		else
			return null;
		
	}
	
	@DeleteMapping(path = "/deleteResMen/{id}")
	public String deleteResManager(@PathVariable Long id) {
		if(id!=null){
		RestaurantManager menager = restaurantManagerService.findOne(id);
		restaurantService.findOne(idRestaurant).getRestaurantManagers().remove(menager);	
		restaurantManagerService.delete(id);
	return "yes";
		}else return "no";	
	}
	
	@DeleteMapping(path = "/deleteRestaurant/{id}")
	public String deleteRestaurant(@PathVariable Long id) {
		if(id!=null){		
			restaurantService.delete(id);
		return "yes";
			}else return "no";	
	}
	
	
}
