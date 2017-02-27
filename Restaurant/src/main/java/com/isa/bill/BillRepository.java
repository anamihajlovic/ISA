package com.isa.bill;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
		
	public Bill findOne(Long id);
}

