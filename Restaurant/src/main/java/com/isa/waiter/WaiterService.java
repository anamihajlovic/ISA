package com.isa.waiter;

public interface WaiterService {
	
	Waiter findByEMail(String email);
	
	Waiter findByEMailAndPassword(String email, String password);
	
	Waiter findOne(Long id);
	
	Waiter save(Waiter waiter);

}
