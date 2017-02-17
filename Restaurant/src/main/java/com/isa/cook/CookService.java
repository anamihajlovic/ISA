package com.isa.cook;

public interface CookService {
	
	Cook findByEMail(String email);
	
	Cook findByEMailAndPassword(String email, String password);
	
	Cook findOne(Long id);
	
	Cook save(Cook cook);

}
