package com.isa.res.segment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class ResSegmentServiceImp implements ResSegmentService {

	private final ResSegmentRepository repository;

	@Autowired
	public ResSegmentServiceImp(ResSegmentRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<ResSegment> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public ResSegment save(ResSegment segment) {
		return repository.save(segment);
	}

	@Override
	public ResSegment findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
		
	}

	@Override
	public ResSegment findbySegType(String s) {
		return repository.findBySegType(s);
	}

}
