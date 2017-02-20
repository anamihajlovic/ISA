package com.isa.work.shift;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="work_shifts")
public class WorkShift {

	
	@Id
	@GeneratedValue
	@Column(name = "work_shift_id")
	private Long id;
	
	@Column  (name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	
	@Column	 (name = "end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;
	
	@Column (name = "waiter")
	private Long idWaiter;
	
	@Column (name = "cook")
	private Long idCook;
	
	@Column (name = "bartender")
	private Long idBartender;
	
	public WorkShift(){}

	public WorkShift(Long id, Date startTime, Date endTime, Long idWaiter, Long idCook, Long idBartender) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.idWaiter = idWaiter;
		this.idCook = idCook;
		this.idBartender = idBartender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getIdWaiter() {
		return idWaiter;
	}

	public void setIdWaiter(Long idWaiter) {
		this.idWaiter = idWaiter;
	}

	public Long getIdCook() {
		return idCook;
	}

	public void setIdCook(Long idCook) {
		this.idCook = idCook;
	}

	public Long getIdBartender() {
		return idBartender;
	}

	public void setIdBartender(Long idBartender) {
		this.idBartender = idBartender;
	}

	
}
