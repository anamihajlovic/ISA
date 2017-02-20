package com.isa.waiter;

import com.isa.employed.Employed;
import com.isa.restaurant.Restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="waiters")
public class Waiter extends Employed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "waiter_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	public Waiter() {}

	public Waiter(Long id) {
		super();
		this.id = id;
		this.restaurant = null;
	}

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



