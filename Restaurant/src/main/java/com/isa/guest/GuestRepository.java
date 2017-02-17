package com.isa.guest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
	
	public Guest findByEmail(String email);
	
	public Guest findByEmailAndPassword(String email, String password);
	
	@SuppressWarnings("unchecked")
	public Guest save(Guest guest);
	
	public Guest findByActivationCode(String activationCode);
	
	public void delete(Long id);
	

}
