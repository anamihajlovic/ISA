package com.isa.bidder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.isa.user.User;

@Entity
@Table(name="bidders")
public class Bidder extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bidder_id")
	private Long id;

	public Bidder(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

}
