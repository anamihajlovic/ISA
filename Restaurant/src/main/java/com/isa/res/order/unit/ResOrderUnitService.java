package com.isa.res.order.unit;

import java.util.List;


public interface ResOrderUnitService {
	List<ResOrderUnit> findAll();

	ResOrderUnit save(ResOrderUnit resOrder);

	ResOrderUnit findOne(Long id);
	
	void delete(Long id);

}
