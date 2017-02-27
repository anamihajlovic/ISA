package com.isa.ordered.dish;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "all_ordered_dishes")
public class OrderedDish {
	
	@Id
	@GeneratedValue
	@Column(name = "ordered_dish_id")
	private Long id;
		
	@NotNull
	@Column(name = "order_id")
	private Long orderId;
		
	@NotNull
	@Column(name = "dish_id")
	private Integer dishId;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name= "dish_status")
	private DishStatus status;
	
	@Column (name = "cook_id", nullable = true)
	private Long cookId;

	
	public OrderedDish() {}		
	
	

	public OrderedDish(Long id, Long orderId, Integer dishId, DishStatus status) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.dishId = dishId;
		this.status = status;
		this.cookId = null;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public Integer getDishId() {
		return dishId;
	}



	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}



	public DishStatus getStatus() {
		return status;
	}



	public void setStatus(DishStatus status) {
		this.status = status;
	}



	public Long getCookId() {
		return cookId;
	}



	public void setCookId(Long cookId) {
		this.cookId = cookId;
	}

	
	
}
