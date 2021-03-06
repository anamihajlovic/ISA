package com.isa.system.manager;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SystemManagerServiceImp implements SystemManagerService{

	
	private final SystemManagerRepository repository;

	@Autowired
	public SystemManagerServiceImp(SystemManagerRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<SystemManager> findAll() {
		return (List<com.isa.system.manager.SystemManager>)repository.findAll();
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
	public SystemManager findOne(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	@Override
	public SystemManager findOneWithMail(String mail) {
		return repository.findByEmail(mail);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}



}
