package com.isa.cook;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.isa.employed.Employed;

@Entity
@Table(name="cooks")
public class Cook extends Employed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cook_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name= "cook_type")
	private CookType cookType;
		

	public Cook() {}		
	
	public Cook(Long id, CookType cookType) {
		super();
		this.id = id;
		this.cookType = cookType;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public CookType getCookType() {
		return cookType;
	}

	public void setCookType(CookType cookType) {
		this.cookType = cookType;
	}

}
