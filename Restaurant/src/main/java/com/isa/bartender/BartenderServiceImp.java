package com.isa.bartender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BartenderServiceImp implements BartenderService {
	
	private final BartenderRepository repository;
	
	@Autowired
	public BartenderServiceImp(final BartenderRepository repository) {
		this.repository = repository;
	}

	@Override
	public Bartender findByEMail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Bartender findByEMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public Bartender findOne(Long id) {		
		return repository.findOne(id);
	}

	@Override
	public void save(Bartender bartender) {		
		repository.save(bartender);
	}
	
	
	

}
