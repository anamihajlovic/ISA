package com.isa.foodstuf;

import java.util.List;


public interface FoodstuffService {

	List<Foodstuff> findAll();

	Foodstuff save(Foodstuff foodstuff);

	Foodstuff findOne(Integer id);
	
	void delete(Integer id);

}
