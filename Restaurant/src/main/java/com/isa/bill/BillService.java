package com.isa.bill;

public interface BillService {
	
	Bill findOne(Long id);
	
	Bill save(Bill bill);

}
