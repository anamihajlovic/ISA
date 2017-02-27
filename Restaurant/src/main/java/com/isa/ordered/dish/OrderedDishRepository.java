package com.isa.ordered.dish;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderedDishRepository extends PagingAndSortingRepository<OrderedDish, Long> {
	
	public OrderedDish findOne(Long id);
	
	public List<OrderedDish> findByOrderId(Long orderId);
	
	public List<OrderedDish> findByOrderIdAndDishId(Long orderId, Integer dishId);
	
	@SuppressWarnings("unchecked")
	public OrderedDish save(OrderedDish orderedDish);
}