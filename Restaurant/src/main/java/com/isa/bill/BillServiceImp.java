package com.isa.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImp implements BillService{

	private final BillRepository repository;
	
	@Autowired
	public BillServiceImp(final BillRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Bill findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public Bill save(Bill bill) {
		// TODO Auto-generated method stub
		return repository.save(bill);
	}
	
	
}
