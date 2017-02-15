package com.isa.bidder;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BidderRepository extends PagingAndSortingRepository<Bidder, Long>{
	
	public Bidder findByEmailAndPassword(String email, String password);
	
	public Bidder findByEmail(String email);		

}
