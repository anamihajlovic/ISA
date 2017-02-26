package com.isa.work.day;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class WorkDayServiceImp implements WorkDayService {

	private final WorkDayRepository repository;
	
	@Autowired
	public WorkDayServiceImp(final WorkDayRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<WorkDay> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public WorkDay save(WorkDay workDay) {
		return repository.save(workDay);
	}

	@Override
	public WorkDay findOne(Long id) {
		return repository.findOne(id);

	}


	
}
