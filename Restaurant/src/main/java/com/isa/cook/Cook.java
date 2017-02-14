package com.isa.cook;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.employed.Employed;

@Entity
@Table(name="cooks")
public class Cook extends Employed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cook_id")
	private Long id;
	
	public Cook() {}
	
	public Cook(Long id) {
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
