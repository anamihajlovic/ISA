package com.isa.drink;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface DrinkRepository extends PagingAndSortingRepository<Drink, Integer> {

	public Drink findByName(String name);
	
	public Drink findOne(Integer id);
}
