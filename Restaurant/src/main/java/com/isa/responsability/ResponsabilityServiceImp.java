package com.isa.responsability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class ResponsabilityServiceImp implements ResponsabilityService{

	private final ResponsabilityRepository repository;

	@Autowired
	public ResponsabilityServiceImp(ResponsabilityRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Responsability> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Responsability save(Responsability res) {
		return repository.save(res);
	}

	@Override
	public Responsability findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

}
