package com.isa.restaurant;

public class RestaurantPojo {

	private String id;


	private String name;	
	

	private String restaurant_type;
	

	private String country;


	private String city;
	

	private String street;
	
	private String rating;
	
	private String restaurantManager;

	
	private String food;

	private String drink;
	
	
	private String bidder;
	
	
	private String waiter;

	
	private String cook;

	
	private String bartender;
	
	public RestaurantPojo(){}

	public RestaurantPojo(String id, String name, String restaurant_type, String country, String city, String street,
			String rating, String restaurantManager, String food, String drink, String bidder, String waiter,
			String cook, String bartender) {
		super();
		this.id = id;
		this.name = name;
		this.restaurant_type = restaurant_type;
		this.country = country;
		this.city = city;
		this.street = street;
		this.rating = rating;
		this.restaurantManager = restaurantManager;
		this.food = food;
		this.drink = drink;
		this.bidder = bidder;
		this.waiter = waiter;
		this.cook = cook;
		this.bartender = bartender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRestaurantManager() {
		return restaurantManager;
	}

	public void setRestaurantManager(String restaurantManager) {
		this.restaurantManager = restaurantManager;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public String getBidder() {
		return bidder;
	}

	public void setBidder(String bidder) {
		this.bidder = bidder;
	}

	public String getWaiter() {
		return waiter;
	}

	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

	public String getBartender() {
		return bartender;
	}

	public void setBartender(String bartender) {
		this.bartender = bartender;
	}

	
	
}
