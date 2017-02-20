package com.isa.dish;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jersey.repackaged.com.google.common.collect.Lists;
@Service
public class DishServiceImp implements DishService{
	
	private final DishRepository repository;

	@Autowired
	public DishServiceImp(DishRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Dish> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Dish save(Dish dish) {
		return repository.save(dish);
	}

	@Override
	public Dish findOne(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
		
	}

}
