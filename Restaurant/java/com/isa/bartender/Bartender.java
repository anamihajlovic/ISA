package com.isa.bartender;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.employed.Employed;

@Entity
@Table(name="bartenders")
public class Bartender extends Employed{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bartender_id")
	private Long id;
	public Bartender() {}


	
}
