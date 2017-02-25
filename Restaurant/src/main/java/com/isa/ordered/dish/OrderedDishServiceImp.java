package com.isa.ordered.dish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class OrderedDishServiceImp implements OrderedDishService {
	
	private final OrderedDishRepository repository;

	@Autowired
	public OrderedDishServiceImp(OrderedDishRepository repository) {		
		this.repository = repository;
	}

	@Override
	public List<OrderedDish> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public OrderedDish save(OrderedDish orderedDish) {
		return repository.save(orderedDish);
	}

	@Override
	public OrderedDish findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<OrderedDish> findByOrderId(Long orderId) {
		return repository.findByOrderId(orderId);
	}

	@Override
	public List<OrderedDish> findByOrderIdAndDishId(Long orderId, Integer dishId) {		
		return repository.findByOrderIdAndDishId(orderId, dishId);
	}


}
