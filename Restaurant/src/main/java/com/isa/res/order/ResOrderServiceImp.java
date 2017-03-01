package com.isa.res.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
@Transactional(isolation=Isolation.SERIALIZABLE)
public class ResOrderServiceImp implements ResOrderService{
	
	private final ResOrderRepository repository;

	@Autowired
	public ResOrderServiceImp(ResOrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ResOrder> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public ResOrder save(ResOrder resOrder) {
		return repository.save(resOrder);
	}

	@Override
	public ResOrder findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}
}
