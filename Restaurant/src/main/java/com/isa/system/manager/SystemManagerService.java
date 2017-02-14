package com.isa.system.manager;

public interface SystemManagerService {
	
	SystemManager findByEMail(String email);
	
	SystemManager findByEMailAndPassword(String email, String password);
	
	/*List<SystemManager> findAll();

	SystemManager save(SystemManager systemManager);

	SystemManager findOne(Long id);

	SystemManager findOne(String mail, String password);

	SystemManager findOneWithMail(String mail);
	
	void delete(Long id);*/

}
