package com.isa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bidders")
public class Bidder extends User {

	public Bidder() {
		
	}
}
