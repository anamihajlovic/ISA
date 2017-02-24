package com.isa.offer.unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.isa.res.order.unit.ResOrderUnit;

@Entity
@Table(name="offer_units")
public class OfferUnit extends ResOrderUnit{
	
	@Column
	@Min(0)
	private Long price;
	
	public OfferUnit(){}

	public OfferUnit(Long price) {
		super();
		this.price = price;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	

}
