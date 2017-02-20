package com.isa.drink;

import java.util.List;

public  interface DrinkService {

	List<Drink> findAll();

	Drink save(Drink drink);

	Drink findOne(Integer id);
	
	void delete(Integer id);

}
