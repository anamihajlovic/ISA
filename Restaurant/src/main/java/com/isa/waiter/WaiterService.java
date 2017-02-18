package com.isa.waiter;

import java.util.List;

import com.isa.system.manager.SystemManager;

public interface WaiterService {
	
	Waiter findByEMail(String email);
	
	Waiter findByEMailAndPassword(String email, String password);
	
	Waiter findOne(Long id);
	
	void save(Waiter waiter);
	
	List<Waiter> findAll();
	
	void delete (Long id);

}
