package com.isa.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


@MappedSuperclass
public class User {


	@Email
	@Column(name="email", unique=true, nullable=false)
	private String email;
	

	@Column(name="password", nullable=false)
	private String password;
	

	@Column(name="first_name", nullable=false)
	private String firstName;
	

	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_role", nullable=false)
	private Role userRole;
	
	
	@Column(name="first_login", nullable=false)

	private Boolean firstLogIn;
	
	
	public User() {}

	public User(String email, String password, String firstName, String lastName, Role userRole) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.firstLogIn = true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}


	public Boolean getFirstLogIn() {
		return firstLogIn;
	}

	public void setFirstLogIn(Boolean firstLogIn) {
		this.firstLogIn = firstLogIn;
	}
	


	
	
	
	
	
}
