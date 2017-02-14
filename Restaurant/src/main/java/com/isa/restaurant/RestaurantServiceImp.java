package com.isa.restaurant;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.restaurant.RestaurantRepository;

import com.isa.restaurant.RestaurantService;
import com.isa.restaurant.Restaurant;

@Service
@Transactional
public class RestaurantServiceImp implements RestaurantService{
	
	private final RestaurantRepository repository;

	@Autowired
	public RestaurantServiceImp(RestaurantRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public List<Restaurant> findAll() {
		return (List<Restaurant>) (repository.findAll());
	}



	@Override
	public Restaurant save(Restaurant restaurant) {
		return repository.save(restaurant);
	}

	@Override
	public Restaurant findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
