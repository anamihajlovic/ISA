package com.isa.res.manager;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.user.User;

@Entity
@Table(name="restaurant_managers")
public class RestaurantManager extends User {	
	
	@Id
	@GeneratedValue
	@Column(name = "res_manager_id")
	private Long id;
	
	@Column(name = "restaurant_id")
	private Long idRestaurant;
	
	public RestaurantManager() {}
	
	public RestaurantManager(Long id,Long idRestaurant) {
		super();
		this.id = id;
		this.idRestaurant = idRestaurant;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}
	

	

}