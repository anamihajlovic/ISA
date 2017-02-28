package com.isa;

public class Rating {

	private String waiter;
	
	private Double waiterRating;
	
	private String restaurant;
	
	private Double resRating;
	
	private String dish;
	
	private Double dishRating;
	
	public Rating(){}

	public Rating(String waiter, Double waiterRating, String restaurant, Double resRating, String dish,
			Double dishRating) {
		super();
		this.waiter = waiter;
		this.waiterRating = waiterRating;
		this.restaurant = restaurant;
		this.resRating = resRating;
		this.dish = dish;
		this.dishRating = dishRating;
	}

	public String getWaiter() {
		return waiter;
	}

	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}

	public Double getWaiterRating() {
		return waiterRating;
	}

	public void setWaiterRating(Double waiterRating) {
		this.waiterRating = waiterRating;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public Double getResRating() {
		return resRating;
	}

	public void setResRating(Double resRating) {
		this.resRating = resRating;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public Double getDishRating() {
		return dishRating;
	}

	public void setDishRating(Double dishRating) {
		this.dishRating = dishRating;
	}
	
	
}
