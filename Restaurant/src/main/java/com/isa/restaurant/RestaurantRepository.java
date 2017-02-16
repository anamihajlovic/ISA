package com.isa.restaurant;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.isa.restaurant.Restaurant;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long>{
	//@Query("select res_manager_id from res_managers_in_restuarants")
	//public List<Long> getEmployedResManagers();

}
