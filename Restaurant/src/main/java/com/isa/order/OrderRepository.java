package com.isa.order;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
	
	public Order findOne(Long id);
	
	public void delete(Long id);

	public List<Order> findByOrderDateAndRestaurantId(Date d, Long i);

}
