package com.isa.restaurant;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.isa.restaurant.Restaurant;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long>{
	
	
}
