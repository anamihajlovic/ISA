package com.isa.bartender;

public interface BartenderService {
	
	Bartender findByEMail(String email);
	
	Bartender findByEMailAndPassword(String email, String password);

}
