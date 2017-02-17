package com.isa.res.manager;


import java.util.List;

public interface RestaurantManagerService {
	
	RestaurantManager findByMail(String mail);
	
	RestaurantManager findByMailAndPassword(String mail, String password);

	List<RestaurantManager> findAll();

	RestaurantManager save(RestaurantManager restaurantManager);

	RestaurantManager findOne(Long id);
	
	void delete(Long id);

	
}
