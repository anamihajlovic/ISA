package com.isa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_managers")
public class RestaurantManager extends User {
	
	public RestaurantManager() {
		
	}

}
