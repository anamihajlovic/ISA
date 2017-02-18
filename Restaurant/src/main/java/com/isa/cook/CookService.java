package com.isa.cook;

import java.util.List;

import com.isa.waiter.Waiter;

public interface CookService {
	
	Cook findByEMail(String email);
	
	Cook findByEMailAndPassword(String email, String password);
	
	Cook findOne(Long id);
	
	void save(Cook cook);
	
	List<Cook> findAll();
	
	void delete (Long id);

}
