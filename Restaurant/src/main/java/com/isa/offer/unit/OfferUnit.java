package com.isa.offer.unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.isa.res.order.unit.ResOrderUnit;

@Entity
@Table(name="offer_units")
public class OfferUnit {
	
	@Id
	@GeneratedValue
	@Column(name = "unit_id")
	private Long id;
	
	
	@NotNull
	@Column
	private String orderFoodstuff;
	
	@NotNull
	@Column
	@Min(0)
	private Integer orderQuantity;
	
	@NotNull
	@Column (name = "res_order")
	private Long ResOrder;
	
	
	@Column
	@Min(0)
	private Long price;
	
	public OfferUnit(){}

	public OfferUnit(Long id, String orderFoodstuff, Integer orderQuantity, Long resOrder, Long price) {
		super();
		this.id = id;
		this.orderFoodstuff = orderFoodstuff;
		this.orderQuantity = orderQuantity;
		ResOrder = resOrder;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderFoodstuff() {
		return orderFoodstuff;
	}

	public void setOrderFoodstuff(String orderFoodstuff) {
		this.orderFoodstuff = orderFoodstuff;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Long getResOrder() {
		return ResOrder;
	}

	public void setResOrder(Long resOrder) {
		ResOrder = resOrder;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	
	
	

}
