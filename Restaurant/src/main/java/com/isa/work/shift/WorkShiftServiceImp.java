package com.isa.work.shift;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WorkShiftServiceImp implements WorkShiftService {

	private final WorkShiftRepository repository;
	
	@Autowired
	public WorkShiftServiceImp(final WorkShiftRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<WorkShift> findAll() {		
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public WorkShift save(WorkShift workShift) {
		return repository.save(workShift);
	}

	@Override
	public WorkShift findOne(Long id) {		
		return repository.findOne(id);
	}


}
