package com.isa.bartender;

import java.util.List;

public interface BartenderService {
	
	Bartender findByEMail(String email);
	
	Bartender findByEMailAndPassword(String email, String password);

	Bartender findOne(Long id);
	
	
	List<Bartender> findAll();
	void delete (Long id);
	Bartender save(Bartender bartender);

}
