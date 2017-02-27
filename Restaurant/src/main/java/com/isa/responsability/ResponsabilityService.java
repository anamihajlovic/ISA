package com.isa.responsability;

import java.util.List;


public interface ResponsabilityService {
	List<Responsability> findAll();

	Responsability save(Responsability res);

	Responsability findOne(Long id);
	
	void delete(Long id);

}
