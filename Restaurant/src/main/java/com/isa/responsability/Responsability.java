package com.isa.responsability;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import com.isa.res.table.ReonTable;
import com.isa.waiter.Waiter;

@Entity
@Table(name="responsabilities")
public class Responsability {
	
	
	@Id
	@GeneratedValue
	@Column(name = "responsability_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ReonTable reon;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "waiter_responsabilities", joinColumns = @JoinColumn(name="responsability_id"), inverseJoinColumns = @JoinColumn(name = "waiter_id"))	
	private List<Waiter> waiters;

	public Responsability(){}
	public Responsability(Long id, ReonTable reon, List<Waiter> waiters) {
		super();
		this.id = id;
		this.reon = reon;
		this.waiters = waiters;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReonTable getReon() {
		return reon;
	}

	public void setReon(ReonTable reon) {
		this.reon = reon;
	}

	public List<Waiter> getWaiters() {
		return waiters;
	}

	public void setWaiters(List<Waiter> waiters) {
		this.waiters = waiters;
	}
	
	

}
