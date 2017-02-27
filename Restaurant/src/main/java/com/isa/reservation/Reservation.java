package com.isa.reservation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reservations")
public class Reservation {
	
	@Id
	@GeneratedValue
	@Column(name = "reservation_id")
	private Long id;
	
	@Column(name = "res_id", nullable=false)
	private Long resId;
	
	@Column(name = "res_name", nullable=false)
	private String resName;
	
	@Column(name = "guest_id", nullable=false)
	private Long guestId;
	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column (name= "date", nullable=false)
	private Date date;
		
	
	@NotNull
	@Column (name= "start_time", nullable=false)
	private String startTime;
	
	@NotNull
	@Column (name= "end_time", nullable=false)
	private String endTime;
	
	@NotNull
	@Column (name= "tables_id", nullable=false)
	private String tables; //oblika idstola1;idstola2;

	
	public Reservation() {}
	
	public Reservation(Long resId, String resName, Long guestId, Date date,  String startTime, String endTime, String tables) {
		this.resId = resId;
		this.resName = resName;
		this.guestId = guestId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tables = tables;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTables() {
		return tables;
	}

	public void setTables(String tables) {
		this.tables = tables;
	}
	
	

}