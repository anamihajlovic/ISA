package com.isa.grade;

import java.util.List;

public interface GradeService {

	Grade save(Grade grade);
	
	List<Grade> findAll();
	
	Grade findByOrderId(Long id);
}
