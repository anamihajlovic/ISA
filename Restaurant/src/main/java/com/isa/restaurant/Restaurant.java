package com.isa.restaurant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.isa.dish.*;
import com.isa.drink.*;
import com.isa.foodstuf.Foodstuff;
import com.isa.res.manager.*;
import com.isa.bartender.*;
import com.isa.bidder.*;
import com.isa.waiter.*;
import com.isa.work.day.WorkDay;
import com.isa.cook.*;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="restaurants")
public class Restaurant {

	@Id
	@GeneratedValue
	@Column(name="restaurant_id", unique=true, nullable=false)
	private Long id;

	@Column
	@NotBlank
	private String name;	
	
	@Column
	@NotBlank
	private String restaurant_type;
	
	@Column
	@NotBlank
	private String country;

	@Column
	@NotBlank
	private String city;
	
	@Column
	@NotBlank
	private String street;
	
	@Column
	private Double ratings;
	
	
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
	

	public Restaurant() {}


	public Restaurant(Long id, String name, String restaurant_type, String country, String city, String street,
			Double ratings, List<RestaurantManager> restaurantManagers, List<Foodstuff> foodstuffs, List<Dish> dishes,
			List<Drink> drinks, List<Bidder> bidders, List<Waiter> waiters, List<Cook> cooks,
			List<Bartender> bartenders, List<WorkDay> workDays) {
		super();
		this.id = id;
		this.name = name;
		this.restaurant_type = restaurant_type;
		this.country = country;
		this.city = city;
		this.street = street;
		this.ratings = ratings;
		this.restaurantManagers = restaurantManagers;
		this.foodstuffs = foodstuffs;
		this.dishes = dishes;
		this.drinks = drinks;
		this.bidders = bidders;
		this.waiters = waiters;
		this.cooks = cooks;
		this.bartenders = bartenders;
		this.workDays = workDays;
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


	public Double getRatings() {
		return ratings;
	}


	public void setRatings(Double ratings) {
		this.ratings = ratings;
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




	
	
}