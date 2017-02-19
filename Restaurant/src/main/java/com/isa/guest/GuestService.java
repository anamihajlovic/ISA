package com.isa.guest;

import java.util.List;

public interface GuestService {
	
	Guest findByEMail(String email);
	
	Guest findByEMailAndPassword(String email, String password);
	
	Guest save(Guest guest);
	
	void activateAccount(String activationCode);
	
	void delete (Long id);
	
	List<Guest> findAll();
	
	Guest findById(Long id);

}
