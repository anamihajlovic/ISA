package com.isa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="waiters")
public class Waiter extends User {
	
	private Date birthDate;
	private int clothesSize;
	private int shoeSize;
	private double rating;
	private Date beginShift;	//treba nam samo vreme pocetka smene
	
	public Waiter() {}

		
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getClothesSize() {
		return clothesSize;
	}

	public void setClothesSize(int clothesSize) {
		this.clothesSize = clothesSize;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Date getBeginShift() {
		return beginShift;
	}

	public void setBeginShift(Date beginShift) {
		this.beginShift = beginShift;
	}	
}
