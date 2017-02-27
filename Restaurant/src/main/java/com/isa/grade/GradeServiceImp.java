package com.isa.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImp implements GradeService {
	
	private final GradeRepository repository;
	
	
	@Autowired
	public GradeServiceImp(GradeRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Grade save(Grade grade) {
		return repository.save(grade);
	}
	
	
}
