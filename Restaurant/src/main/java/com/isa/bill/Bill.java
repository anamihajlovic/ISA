package com.isa.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bills")
public class Bill {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bill_id")
	private Long id;
	
	@Column (name = "bill_date", nullable = false)
	private String billDate;
	
	@Column(name="bill_time", nullable = false)
	private String time;
	
	@Column(name = "waiter_id", nullable = false)
	private Long waiterId;
	
	@Column(name = "total_price", nullable = false)
	private Long totalPrice;

	public Bill(Long id, String billDate, String time, Long waiterId, Long totalPrice) {
		super();
		this.id = id;
		this.billDate = billDate;
		this.time = time;
		this.waiterId = waiterId;
		this.totalPrice = totalPrice;
	}
	
	public Bill() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
}
