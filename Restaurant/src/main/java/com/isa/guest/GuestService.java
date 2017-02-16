package com.isa.guest;

public interface GuestService {
	
	Guest findByEMail(String email);
	
	Guest findByEMailAndPassword(String email, String password);
	
	Guest save(Guest guest);

}
