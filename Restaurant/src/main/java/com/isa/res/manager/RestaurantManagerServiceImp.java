package com.isa.res.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RestaurantManagerServiceImp  implements RestaurantManagerService{

	private final RestaurantManagerRepository repository;

	@Autowired
	public RestaurantManagerServiceImp(RestaurantManagerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public RestaurantManager findByMail(String mail) {
		return repository.findByEmail(mail);
	}
	
	@Override
	public RestaurantManager findByMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public List<RestaurantManager> findAll() {
		return (List<RestaurantManager>) (repository.findAll());
	}




	@Override
	public RestaurantManager save(RestaurantManager restaurantManager) {
		return repository.save(restaurantManager);
	}

	@Override
	public RestaurantManager findOne(Long id) {
		return repository.findOne(id);
	}

	

	
}
