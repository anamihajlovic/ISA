package com.isa.dish;

import java.util.List;

public interface DishService {

	List<Dish> findAll();

	Dish save(Dish dish);

	Dish findOne(Integer id);
	
	void delete(Integer id);
}
