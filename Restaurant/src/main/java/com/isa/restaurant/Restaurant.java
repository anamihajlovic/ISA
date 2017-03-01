package com.isa.restaurant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.isa.bartender.Bartender;
import com.isa.bidder.Bidder;
import com.isa.bill.Bill;
import com.isa.cook.Cook;
import com.isa.dish.Dish;
import com.isa.drink.Drink;
import com.isa.foodstuf.Foodstuff;
import com.isa.res.manager.RestaurantManager;
import com.isa.res.order.ResOrder;
import com.isa.res.segment.ResSegment;
import com.isa.waiter.Waiter;
import com.isa.work.day.WorkDay;


@Entity
@Table(name="restaurants")
public class Restaurant {

	@Id
	@GeneratedValue
	@Column(name="restaurant_id", unique=true, nullable=false)
	private Long id;

	@Column
	@NotNull
	private String name;	
	
	@Column
	@NotNull
	private String restaurant_type;
	
	@Column
	@NotNull
	private String country;

	@Column
	@NotNull
	private String city;
	
	@Column
	@NotNull
	private String street;
	
	@Column
	@NotNull
	private Integer number;
	
	@Column
	private Double ratings;
	
	@Column
	private Double latitude;
	
	@Column
	private Double longitude;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "res_managers_in_restuarants", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "res_manager_id"))
	private List<RestaurantManager> restaurantManagers;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "foodstuffs_in_restuarants", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "foodstuff_id"))
	private List<Foodstuff> foodstuffs;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restuarant_menu", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "dish_id"))
	private List<Dish> dishes;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_drink_card", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "drink_id"))
	private List<Drink> drinks;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_bidders", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "bidder_id"))
	private List<Bidder> bidders;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_waiters", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "waiter_id"))
	private List<Waiter> waiters;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_cooks", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "cook_id"))
	private List<Cook> cooks;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_bartenders", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "bartender_id"))
	private List<Bartender> bartenders;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_work_days", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "work_day_id"))
	private List<WorkDay> workDays;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_segments", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"))
	private List<ResSegment> segments;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_orders", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private List<ResOrder> orders;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restaurant_bills", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "bill_id"))
	private List<Bill> bills;
	

	public Restaurant() {
		this.restaurantManagers = new ArrayList<RestaurantManager>();
		this.waiters = new ArrayList<Waiter>();
		this.cooks = new ArrayList<Cook>();
		this.bartenders = new ArrayList<Bartender>();
		this.bidders = new ArrayList<Bidder>();
		this.segments = new ArrayList<ResSegment>();
		this.orders =new ArrayList<ResOrder>();
	}


	public Restaurant(Long id, String name, String restaurant_type, String country, String city, String street,
			Integer number, Double ratings, Double latitude, Double longitude,
			List<RestaurantManager> restaurantManagers, List<Foodstuff> foodstuffs, List<Dish> dishes,
			List<Drink> drinks, List<Bidder> bidders, List<Waiter> waiters, List<Cook> cooks,
			List<Bartender> bartenders, List<WorkDay> workDays, List<ResSegment> segments, List<ResOrder> orders,
			List<Bill> bills) {
		super();
		this.id = id;
		this.name = name;
		this.restaurant_type = restaurant_type;
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.ratings = ratings;
		this.latitude = latitude;
		this.longitude = longitude;
		this.restaurantManagers = restaurantManagers;
		this.foodstuffs = foodstuffs;
		this.dishes = dishes;
		this.drinks = drinks;
		this.bidders = bidders;
		this.waiters = waiters;
		this.cooks = cooks;
		this.bartenders = bartenders;
		this.workDays = workDays;
		this.segments = segments;
		this.orders = orders;
		this.bills = bills;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRestaurant_type() {
		return restaurant_type;
	}


	public void setRestaurant_type(String restaurant_type) {
		this.restaurant_type = restaurant_type;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public Double getRatings() {
		return ratings;
	}


	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public List<RestaurantManager> getRestaurantManagers() {
		return restaurantManagers;
	}


	public void setRestaurantManagers(List<RestaurantManager> restaurantManagers) {
		this.restaurantManagers = restaurantManagers;
	}


	public List<Foodstuff> getFoodstuffs() {
		return foodstuffs;
	}


	public void setFoodstuffs(List<Foodstuff> foodstuffs) {
		this.foodstuffs = foodstuffs;
	}


	public List<Dish> getDishes() {
		return dishes;
	}


	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}


	public List<Drink> getDrinks() {
		return drinks;
	}


	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}


	public List<Bidder> getBidders() {
		return bidders;
	}


	public void setBidders(List<Bidder> bidders) {
		this.bidders = bidders;
	}


	public List<Waiter> getWaiters() {
		return waiters;
	}


	public void setWaiters(List<Waiter> waiters) {
		this.waiters = waiters;
	}


	public List<Cook> getCooks() {
		return cooks;
	}


	public void setCooks(List<Cook> cooks) {
		this.cooks = cooks;
	}


	public List<Bartender> getBartenders() {
		return bartenders;
	}


	public void setBartenders(List<Bartender> bartenders) {
		this.bartenders = bartenders;
	}


	public List<WorkDay> getWorkDays() {
		return workDays;
	}


	public void setWorkDays(List<WorkDay> workDays) {
		this.workDays = workDays;
	}


	public List<ResSegment> getSegments() {
		return segments;
	}


	public void setSegments(List<ResSegment> segments) {
		this.segments = segments;
	}


	public List<ResOrder> getOrders() {
		return orders;
	}


	public void setOrders(List<ResOrder> orders) {
		this.orders = orders;
	}


	public List<Bill> getBills() {
		return bills;
	}


	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}


	
	

		

}