package com.isa.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.isa.dish.Dish;
import com.isa.drink.Drink;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long id;
	
	@Column(name="restaurant_id")
	private Long restaurantId;
	
	@Enumerated(EnumType.STRING)
	@NotNull		
	@Column(name = "drinks_status")
	private OrderItemStatus drinksStatus;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinTable(name = "ordered_drinks", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "drink_id"))
	private List<Drink> orderedDrinks;
	
	@Enumerated(EnumType.STRING)
	@NotNull		
	@Column(name = "dish_status")
	private OrderItemStatus dishStatus;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinTable(name = "ordered_dish", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "dish_id"))
	private List<Dish> orderedDish;
	
	public Order() {}
	
	
	public Order(Long id, Long restaurantId, OrderItemStatus drinksStatus, List<Drink> orderedDrinks, OrderItemStatus dishStatus,
			List<Dish> orderedDish) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.drinksStatus = drinksStatus;
		this.orderedDrinks = orderedDrinks;
		this.dishStatus = dishStatus;
		this.orderedDish = orderedDish;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Drink> getOrderedDrinks() {
		return orderedDrinks;
	}

	public void setOrderedDrinks(List<Drink> orderedDrinks) {
		this.orderedDrinks = orderedDrinks;
	}		

	public List<Dish> getOrderedDish() {
		return orderedDish;
	}


	public void setOrderedDish(List<Dish> orderedDish) {
		this.orderedDish = orderedDish;
	}


	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}


	public OrderItemStatus getDrinksStatus() {
		return drinksStatus;
	}


	public void setDrinksStatus(OrderItemStatus drinksStatus) {
		this.drinksStatus = drinksStatus;
	}

	public OrderItemStatus getDishStatus() {
		return dishStatus;
	}

	public void setDishStatus(OrderItemStatus dishStatus) {
		this.dishStatus = dishStatus;
	}

	
}
