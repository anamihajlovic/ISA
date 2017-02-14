package com.isa.res.manager;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.user.User;

@Entity
@Table(name="restaurant_managers")
public class RestaurantManager extends User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "res_manager_id")
	private Long id;
	
	public RestaurantManager() {}
	
	public RestaurantManager(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "RestauranManager [id=" + id + "]";
	}


	

}