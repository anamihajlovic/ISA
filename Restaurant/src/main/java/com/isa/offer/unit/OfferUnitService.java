package com.isa.offer.unit;

import java.util.List;


public interface OfferUnitService {

	List<OfferUnit> findAll();

	OfferUnit save(OfferUnit offer);

	OfferUnit findOne(Long id);
	
	void delete(Long id);
}
