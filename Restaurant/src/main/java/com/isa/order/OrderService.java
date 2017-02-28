package com.isa.order;

import java.util.Date;
import java.util.List;

public interface OrderService {

	List<Order> findAll();
	
	Order save(Order order);
	
	Order findOne(Long id);
	
	List<Order> findByOrderDateAndRestaurantId(Date d,Long i);
	
	void delete(Long id);

	
}
