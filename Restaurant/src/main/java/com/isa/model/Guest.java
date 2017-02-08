package com.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="guests")
public class Guest extends User {
	@Column
	private String test;
	

	public Guest() {}


	public String getTest() {
		return test;
	}


	public void setTest(String test) {
		this.test = test;
	}
	
	
}
