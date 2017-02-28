package com.isa.res.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.res.order.unit.ResOrderUnit;

@Entity
@Table(name="res_orders")
public class ResOrder {
	
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@Column (name="restaurant_name")
	private String resName;
	
	@Column (name="end_date")
	private String endDate;
		
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_foodstuffs", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "unit_id"))
	private List<ResOrderUnit> resOrderFoodstuffs;

	public ResOrder (){}

	public ResOrder(Long id, String resName, String endDate, List<ResOrderUnit> resOrderFoodstuffs
			) {
		super();
		this.id = id;
		this.resName = resName;
		this.endDate = endDate;
		this.resOrderFoodstuffs = resOrderFoodstuffs;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<ResOrderUnit> getResOrderFoodstuffs() {
		return resOrderFoodstuffs;
	}

	public void setResOrderFoodstuffs(List<ResOrderUnit> resOrderFoodstuffs) {
		this.resOrderFoodstuffs = resOrderFoodstuffs;
	}


	
	


}
