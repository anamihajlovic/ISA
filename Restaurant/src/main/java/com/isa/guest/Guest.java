package com.isa.guest;

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
	
	@Column(name = "guest_aCode", nullable=true)
	private String activationCode;
	
	@Column(name="active", nullable=true)
	private Boolean active;
	

	public Guest() {}
	
	public Guest(Long id) {
		this.id = id;
		this.active = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

	
	
}
