package com.isa.waiter;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WaiterRepository extends PagingAndSortingRepository<Waiter, Long> {
	
	public Waiter findByEmail(String email);

	public Waiter findByEmailAndPassword(String email, String password);

}
