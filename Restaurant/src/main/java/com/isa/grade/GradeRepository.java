package com.isa.grade;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GradeRepository extends PagingAndSortingRepository<Grade, Long> {

	@SuppressWarnings("unchecked")
	public Grade save(Grade grade);

	public Grade findByOrderId(Long id);
	
	
}
