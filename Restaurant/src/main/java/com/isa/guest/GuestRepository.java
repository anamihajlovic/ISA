package com.isa.guest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
	
	public Guest findByEmail(String email);
	
	public Guest findByEmailAndPassword(String email, String password);
	
	@SuppressWarnings("unchecked")
	public Guest save(Guest guest);
	
	public Guest findByActivationCode(String activationCode);
	
	public void delete(Long id);
	
	public List<Guest> findAll();
	
	public Guest findById(Long id);
	

}
