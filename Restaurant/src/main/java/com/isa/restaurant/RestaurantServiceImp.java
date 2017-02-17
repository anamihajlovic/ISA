package com.isa.restaurant;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.isa.restaurant.RestaurantRepository;

import com.isa.restaurant.RestaurantService;

import jersey.repackaged.com.google.common.collect.Lists;


@Service
@Transactional
public class RestaurantServiceImp implements RestaurantService{

	@Autowired
	private RestaurantRepository repository;

	@Override
	public List<Restaurant> findAll() {
		return Lists.newArrayList(repository.findAll());
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


	@Override
	public void deleteResManager(Long id, Long idd) {
		// TODO Auto-generated method stub
		repository.findOne(id).getRestaurantManagers().remove(idd);
		
	}



	/*@Override
	public List<Long> findEmployedResManagers() {
		return repository.getEmployedResManagers();
		
	}*/

}
