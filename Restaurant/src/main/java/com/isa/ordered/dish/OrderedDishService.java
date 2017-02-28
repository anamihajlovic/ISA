package com.isa.ordered.dish;

import java.util.List;

public interface OrderedDishService {

	List<OrderedDish> findAll();
	
	OrderedDish save(OrderedDish orderedDish);
	
	OrderedDish findOne(Long id);
	
	List<OrderedDish> findByOrderId(Long orderId);
	
	List<OrderedDish> findByOrderIdAndDishId(Long orderId, Integer dishId);
	
	void delete(Long id);
	
}
