package com.isa.res.order.unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import static javax.persistence.InheritanceType.JOINED;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="res_order_units")
public class ResOrderUnit {
	
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
	

	public ResOrderUnit (){}


	public ResOrderUnit(Long id, String orderFoodstuff, Integer orderQuantity, Long resOrder) {
		super();
		this.id = id;
		this.orderFoodstuff = orderFoodstuff;
		this.orderQuantity = orderQuantity;
		ResOrder = resOrder;
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
	
	

}
