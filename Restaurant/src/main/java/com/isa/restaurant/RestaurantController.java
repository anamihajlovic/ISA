package com.isa.restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dish.Dish;
import com.isa.drink.Drink;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	private final RestaurantService restaurantService;
	
	@Autowired
	public RestaurantController(final RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping(path = "/getAll")
	public List<Restaurant> getAllRestaurants() {
	    List<Restaurant> restaurants = sortRestaurants("name", false);
		return restaurants;
	}
	
	@GetMapping(path = "/sort/{sortBy}/{reverse}")
	private List<Restaurant> sortRestaurants(@PathVariable String sortBy, @PathVariable boolean reverse) {
		List<Restaurant> restaurants = restaurantService.findAll();
		
		Collections.sort(restaurants, new Comparator<Restaurant>() {

			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				if (sortBy.equals("name"))
					return o1.getName().compareTo(o2.getName());
				else if (sortBy.equals("type"))
					return o1.getRestaurant_type().compareTo(o2.getRestaurant_type());
				else if (sortBy.equals("street"))
					return o1.getStreet().compareTo(o2.getStreet());
				else if (sortBy.equals("city"))
					return o1.getCity().compareTo(o2.getStreet());
				else if (sortBy.equals("country"))
					return o1.getCountry().compareTo(o2.getCountry());
				else if (sortBy.equals("rate"))
					return Double.compare(o1.getRatings(), o2.getRatings());
				else
					return 0;
				
			}
		});

		if (reverse)
			Collections.reverse(restaurants);

		return restaurants;
	}
	
	@GetMapping(path = "/dishes/{id}")
	public List<Dish> getDishes(@PathVariable Long id) {
		System.out.println("Pogodjena metoda getDishes " + id);
		List<Dish> dishes =restaurantService.findOne(id).getDishes();	
		return dishes;
		
	}
	
	@GetMapping(path = "/drinks/{id}")
	public List<Drink> getDrinks(@PathVariable Long id) {
		System.out.println("Pogodjena metoda getDishes " + id);
		List<Drink> dishes =restaurantService.findOne(id).getDrinks();	
		return dishes;
		
	}
	
	@GetMapping(path = "/getRestaurant/{id}")
	public Restaurant getRestaurant(@PathVariable Long id) {
		return restaurantService.findOne(id);
	}


}
