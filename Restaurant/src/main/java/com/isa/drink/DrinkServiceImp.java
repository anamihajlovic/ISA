package com.isa.drink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class DrinkServiceImp implements DrinkService {
	private final DrinkRepository repository;

	@Autowired
	public DrinkServiceImp(DrinkRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Drink> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Drink save(Drink drink) {
		return repository.save(drink);
	}

	@Override
	public Drink findOne(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
		
	}

}
