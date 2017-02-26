package com.isa.res.order.unit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class ResOrderUnitServiceImp implements ResOrderUnitService {

	private final ResOrderUnitRepository repository;

	@Autowired
	public ResOrderUnitServiceImp(ResOrderUnitRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ResOrderUnit> findAll() {
		return Lists.newArrayList(repository.findAll());
	}
	
	@Override
	public ResOrderUnit save(ResOrderUnit resOrder) {
		return repository.save(resOrder);
	}

	@Override
	public ResOrderUnit findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}
	
}
