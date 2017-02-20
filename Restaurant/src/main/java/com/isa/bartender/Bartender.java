package com.isa.bartender;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.employed.Employed;
import com.isa.restaurant.Restaurant;

@Entity
@Table(name="bartenders")
public class Bartender extends Employed{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bartender_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	
	public Bartender() {}
	
	
	
	
	public Long getId() {
		return id;		
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
	


	
}
