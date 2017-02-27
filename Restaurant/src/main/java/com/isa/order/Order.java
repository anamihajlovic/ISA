package com.isa.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.isa.dish.Dish;
import com.isa.drink.Drink;
import com.isa.ordered.dish.DishStatus;
import com.isa.ordered.dish.OrderedDish;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long id;
	
	@Column(name="restaurant_id")
	private Long restaurantId;
	
	@Column (name = "acceptance_time", nullable = true)
	private String acceptanceTime;
	
	@Column(name="accepted_waiter", nullable = true)
	private Long waiterId;
		
	@Column (name= "order_date")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status", nullable = false)
	private OrderStatus orderStatus;	
	
	@Enumerated(EnumType.STRING)		
	@Column(name = "drinks_status", nullable = false)
	private OrderItemStatus drinksStatus;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinTable(name = "ordered_drinks", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "drink_id"))
	private List<Drink> orderedDrinks;
	
	@Enumerated(EnumType.STRING)
	@NotNull		
	@Column(name = "dish_status")
	private OrderItemStatus dishStatus;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinTable(name = "ordered_dish", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "dish_order_id"))
	private List<OrderedDish> orderedDish;
	
	@Transient
	private List<Dish> dishes = new ArrayList<Dish>();
	
	@Transient 
	private List<Drink> drinks = new ArrayList<Drink>();
	
	@Transient
	private HashMap<Integer, Integer> dishQuantity = new HashMap<Integer, Integer>();
	
	@Transient
	private HashMap<Integer, DishStatus> dishStatusMap = new HashMap<Integer, DishStatus>();
	
	@Transient
	private HashMap<Integer, Integer> drinkQuantity = new HashMap<Integer, Integer>();
	
	
			
	public Order() {}	
	
	

	public Order(Long id, Long restaurantId, Long waiterId, String acceptanceTime, Date orderDate, OrderStatus orderStatus, OrderItemStatus drinksStatus,
			List<Drink> orderedDrinks, OrderItemStatus dishStatus, List<OrderedDish> orderedDish) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.waiterId = waiterId;
		this.acceptanceTime = acceptanceTime;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
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

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}	

	public Long getWaiterId() {
		return waiterId;
	}
	

	public String getAcceptanceTime() {
		return acceptanceTime;
	}



	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}



	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
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

	public List<OrderedDish> getOrderedDish() {
		return orderedDish;
	}

	public void setOrderedDish(List<OrderedDish> orderedDish) {
		this.orderedDish = orderedDish;
	}

	

	public Date getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public List<Dish> getDishes() {
		return dishes;
	}


	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}


	public HashMap<Integer, Integer> getDishQuantity() {
		return dishQuantity;
	}


	public void setDishQuantity(HashMap<Integer, Integer> dishQuantity) {
		this.dishQuantity = dishQuantity;
	}


	public HashMap<Integer, Integer> getDrinkQuantity() {
		return drinkQuantity;
	}


	public void setDrinkQuantity(HashMap<Integer, Integer> drinkQuantity) {
		this.drinkQuantity = drinkQuantity;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}



	public HashMap<Integer, DishStatus> getDishStatusMap() {
		return dishStatusMap;
	}



	public void setDishStatusMap(HashMap<Integer, DishStatus> dishStatusMap) {
		this.dishStatusMap = dishStatusMap;
	}



	public OrderStatus getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
	
}
