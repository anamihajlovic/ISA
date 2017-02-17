package com.isa.waiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.bartender.Bartender;

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
	public void save(Waiter waiter) {		
		repository.save(waiter);
	}

}
