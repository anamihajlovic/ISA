package com.isa.offer;

import java.util.List;

public interface OfferService {
	List<Offer> findAll();

	Offer save(Offer offer);

	Offer findOne(Long id);
	
	void delete(Long id);
}
