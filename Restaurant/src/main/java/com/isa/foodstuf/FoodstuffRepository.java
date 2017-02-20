package com.isa.foodstuf;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodstuffRepository extends PagingAndSortingRepository<Foodstuff, Integer>{

	public Foodstuff findByName(String name);
	
	public Foodstuff findOne(Integer id);
}
