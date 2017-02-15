package com.isa.bartender;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BartenderRepository extends PagingAndSortingRepository<Bartender, Long> {
	
	public Bartender findByEmail(String email);
	
	public Bartender findByEmailAndPassword(String email, String password);


}
