package com.isa.system.manager;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface SystemManagerRepository extends PagingAndSortingRepository<SystemManager, Long> {

public SystemManager findByEmail(String email);

public SystemManager findByEmailAndPassword(String email, String password);

	
}
