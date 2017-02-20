package com.isa.foodstuf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.isa.victual.Victual;

@Entity
@Table(name="foodstuffs")
public class Foodstuff  extends Victual{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "foodstuff_id")
	private Integer id;

	@NotNull
	@Column
	@Min(0)
	private Integer quantity;

	

	public Foodstuff(String name, Integer id, Integer quantity) {
		super(name);
		this.id = id;
		this.quantity = quantity;
	}

	public Foodstuff() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	
	
	

}
