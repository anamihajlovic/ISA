package com.isa.system.manager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class SystemManagerServiceImp implements SystemManagerService {

	
	private final SystemManagerRepository repository;

	@Autowired
	public SystemManagerServiceImp(SystemManagerRepository repository) {
		this.repository = repository;
	}

	@Override
	public SystemManager findByEMail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public SystemManager findByEMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public List<SystemManager> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public SystemManager save(SystemManager systemManager) {
		return repository.save(systemManager);
	}

	@Override
	public SystemManager findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}
	


}
