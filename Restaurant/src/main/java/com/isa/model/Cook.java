package com.isa.model;

import java.util.Date;

public class Cook extends User {
	
	private Date birthDate;
	private int clothesSize;
	private int shoeSize;
	
	public Cook() {}

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

}
