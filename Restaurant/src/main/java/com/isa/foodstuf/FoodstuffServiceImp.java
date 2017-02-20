package com.isa.foodstuf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class FoodstuffServiceImp implements FoodstuffService{
	
	private final FoodstuffRepository repository;

	@Autowired
	public FoodstuffServiceImp(FoodstuffRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Foodstuff> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Foodstuff save(Foodstuff foodstuff) {
		return repository.save(foodstuff);
	}

	@Override
	public Foodstuff findOne(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
		
		
	}

}
