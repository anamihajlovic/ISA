package com.isa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="guests")
public class Guest extends User {
	
	

	public Guest() {}
}
