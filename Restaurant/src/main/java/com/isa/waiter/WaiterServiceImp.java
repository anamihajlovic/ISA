package com.isa.waiter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class WaiterServiceImp implements WaiterService {
	
	private final WaiterRepository repository;
	
	@Autowired
	public WaiterServiceImp(final WaiterRepository repository) {
		this.repository = repository;
	}

	@Override
	public Waiter findByEMail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Waiter findByEMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
	@Override
	public Waiter findOne(Long id) {		
		return repository.findOne(id);
	}

	@Override
	public Waiter save(Waiter waiter) {		
		return repository.save(waiter);
	}

	@Override
	public List<Waiter> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

}
