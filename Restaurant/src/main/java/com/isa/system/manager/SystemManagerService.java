package com.isa.system.manager;

import java.util.List;

public interface SystemManagerService {
	
	SystemManager findByEMail(String email);
	
	SystemManager findByEMailAndPassword(String email, String password);
	
	List<SystemManager> findAll();

	SystemManager save(SystemManager systemManager);

	SystemManager findOne(Long id);
	
	void delete(Long id);

}
