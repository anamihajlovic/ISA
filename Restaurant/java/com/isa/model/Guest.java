package com.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.user.User;

@Entity
@Table(name="guests")
public class Guest extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "guest_id")
	private Long id;
	

	public Guest() {}

	
	
}
