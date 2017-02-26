package com.isa.res.order;

import java.util.List;


public interface ResOrderService {
	List<ResOrder> findAll();

	ResOrder save(ResOrder resOrder);

	ResOrder findOne(Long id);
	
	void delete(Long id);
}
