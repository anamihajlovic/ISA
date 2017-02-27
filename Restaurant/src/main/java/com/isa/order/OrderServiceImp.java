package com.isa.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class OrderServiceImp implements OrderService {
	
	private final OrderRepository repository;

	@Autowired
	public OrderServiceImp(OrderRepository repository) {		
		this.repository = repository;
	}

	@Override
	public List<Order> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Order save(Order order) {
		return repository.save(order);
	}

	@Override
	public Order findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
	
	

}
