package com.isa.employed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.isa.restaurant.Restaurant;
import com.isa.user.User;


@MappedSuperclass
public class Employed extends User{
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name = "clothesSize")
	private ClothesSize clothesSize;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name = "shoesSize")
	private ShoesSize shoesSize;
	
	@Column
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date birthday;	
		
	@NotNull
	@Column(name = "restaurant_id")
	private Long restaurantId;
	
	
	public Employed() {}

	public Employed(ClothesSize clothesSize, ShoesSize shoesSize, Date birthday, Long restaurantId) {
		super();
		this.clothesSize = clothesSize;
		this.shoesSize = shoesSize;
		this.birthday = birthday;	
		this.restaurantId = restaurantId;
	}

	public ClothesSize getClothesSize() {
		return clothesSize;
	}

	public void setClothesSize(ClothesSize clothesSize) {
		this.clothesSize = clothesSize;
	}

	public ShoesSize getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(ShoesSize shoesSize) {
		this.shoesSize = shoesSize;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}			
	
}
