package com.isa.order;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
	
	public Order findOne(Long id);
	
	public void delete(Long id);

}
