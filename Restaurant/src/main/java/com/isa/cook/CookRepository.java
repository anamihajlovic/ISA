package com.isa.cook;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CookRepository extends PagingAndSortingRepository<Cook, Long> {
	
	public Cook findByEmailAndPassword(String email, String password);
	
	public Cook findByEmail(String email);

}
