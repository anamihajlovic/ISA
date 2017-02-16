package com.isa.employed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
	
	public Employed() {}

	public Employed(ClothesSize clothesSize, ShoesSize shoesSize, Date birthday) {
		super();
		this.clothesSize = clothesSize;
		this.shoesSize = shoesSize;
		this.birthday = birthday;
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


	

}
