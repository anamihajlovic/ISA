package com.isa.restaurant;

import java.util.List;


import com.isa.restaurant.Restaurant;

public interface RestaurantService {

	List<Restaurant> findAll();
	

	Restaurant save(Restaurant restaurant);

	Restaurant findOne(Long id);

	void delete(Long id);
	
	void deleteResManager(Long id, Long idd);
	//List<Long> findEmployedResManagers();
}
