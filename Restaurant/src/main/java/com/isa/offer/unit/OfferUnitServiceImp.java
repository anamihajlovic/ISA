package com.isa.offer.unit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;


@Service
public class OfferUnitServiceImp  implements  OfferUnitService{

	private final OfferUnitRepository repository;

	@Autowired
	public OfferUnitServiceImp(OfferUnitRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<OfferUnit> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public OfferUnit save(OfferUnit offer) {
		return repository.save(offer);
	}

	@Override
	public OfferUnit findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

}
