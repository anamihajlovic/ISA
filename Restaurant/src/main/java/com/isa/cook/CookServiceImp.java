package com.isa.cook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookServiceImp implements CookService {
	
	private final CookRepository repository;
	
	@Autowired
	public CookServiceImp(final CookRepository repository) {
		this.repository = repository;
	}

	@Override
	public Cook findByEMail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Cook findByEMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

}
