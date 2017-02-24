package com.isa.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class OfferServiceImp implements OfferService{
	
	private final OfferRepository repository;

	@Autowired
	public OfferServiceImp(OfferRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Offer> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Offer save(Offer offer) {
		return repository.save(offer);
	}

	@Override
	public Offer findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}
	

}
