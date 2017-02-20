package com.isa.drink;

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
@Table(name="drinks")
public class Drink extends Victual{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "drink_id")
	private Integer id;
	
	@NotBlank
	@Column
	private String text;
	
	@NotNull
	@Column
	@Min(0)
	private Long price;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name= "drink_type")
	private DrinkType drinkType;

	
	public Drink(String name, Integer id, String text, DrinkType drinkType) {
		super(name);
		this.id = id;
		this.text = text;
		this.drinkType = drinkType;
	}

	public Drink() {

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

	public DrinkType getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(DrinkType drinkType) {
		this.drinkType = drinkType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	
}	