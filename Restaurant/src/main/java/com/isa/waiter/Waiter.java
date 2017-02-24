package com.isa.waiter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.employed.Employed;

@Entity
@Table(name="waiters")
public class Waiter extends Employed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "waiter_id")
	private Long id;
	
	public Waiter() {}

	public Waiter(Long id) {
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



