package com.isa.res.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
@Transactional(isolation=Isolation.SERIALIZABLE)
public class ResTableServiceImp  implements ResTableService{
	
	private final ResTableRepository repository;

	@Autowired
	public ResTableServiceImp(ResTableRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ResTable> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public ResTable save(ResTable table) {
		return repository.save(table);
	}

	@Override
	public ResTable findOne(Long id) {
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

}
