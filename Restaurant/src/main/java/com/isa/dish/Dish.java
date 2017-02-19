package com.isa.dish;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.isa.victual.Victual;

@Entity
@Table(name="dishes")
public class Dish extends Victual{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dish_id")
	private Integer id;

	@NotBlank
	@Column
	private String text;
	
	@NotNull
	@Column
	@Min(0)
	private Long price;
	
	@Enumerated(EnumType.STRING)
	@NotBlank
	@Column (name= "dish_type")
	private DishType dishType;

	public Dish(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Dish(String name, Integer id, String text, DishType dishType) {
		super(name);
		this.id = id;
		this.text = text;
		this.dishType = dishType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DishType getDishType() {
		return dishType;
	}

	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	


	

}