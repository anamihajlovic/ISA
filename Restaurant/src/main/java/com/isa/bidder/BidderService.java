package com.isa.bidder;

public interface BidderService {
	
	Bidder findByEMail(String email);
	
	Bidder findByEMailAndPassword(String email, String password);

   /*List<Bidder> findAll();

	Bidder save(Bidder bidder);

	Bidder findOne(Long id);

	Bidder findOne(String mail, String password);

	Bidder findOneWithMail(String mail);

	void delete(Long id);*/

}
