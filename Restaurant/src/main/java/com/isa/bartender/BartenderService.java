package com.isa.bartender;

public interface BartenderService {
	
	Bartender findByEMail(String email);
	
	Bartender findByEMailAndPassword(String email, String password);

	Bartender findOne(Long id);
	
	void save(Bartender bartender);
}
