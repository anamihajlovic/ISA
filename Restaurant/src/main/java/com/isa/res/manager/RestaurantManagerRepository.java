package com.isa.res.manager;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface RestaurantManagerRepository extends PagingAndSortingRepository<RestaurantManager, Long> {

	public RestaurantManager findByEmailAndPassword(String email, String password);

	public RestaurantManager findByEmail(String email);


}