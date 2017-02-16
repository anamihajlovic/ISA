package com.isa.guest;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegisterData {
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String confirm;
	
	public RegisterData() {}
	
	public RegisterData(String firstName, String lastName, String email, String password, String confirm) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password= password;
		this.confirm = confirm;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	

}
