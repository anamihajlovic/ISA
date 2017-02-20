package com.isa.dish;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DishRepository extends PagingAndSortingRepository<Dish, Integer>{


	public Dish findByName(String name);
	
	public Dish findOne(Integer id);
}
