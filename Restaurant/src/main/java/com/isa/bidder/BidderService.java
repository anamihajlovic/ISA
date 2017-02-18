package com.isa.bidder;

import java.util.List;

public interface BidderService {
	
	Bidder findByEMail(String email);
	
	Bidder findByEMailAndPassword(String email, String password);

    List<Bidder> findAll();

	Bidder save(Bidder bidder);

	Bidder findOne(Long id);

	void delete(Long id);

}
