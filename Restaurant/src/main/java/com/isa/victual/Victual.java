package com.isa.victual;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public class Victual {
	
	@NotBlank
	@Column
	private String name;


	public Victual(){}
	
	public Victual(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
