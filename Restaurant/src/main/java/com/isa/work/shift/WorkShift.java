package com.isa.work.shift;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.isa.bartender.Bartender;
import com.isa.cook.Cook;
import com.isa.waiter.Waiter;

@Entity
@Table(name="work_shifts")
public class WorkShift {

	
	@Id
	@GeneratedValue
	@Column(name = "work_shift_id")
	private Long id;
	
	@Column  (name = "start_time")
	private String startTime;
	
	@Column	 (name = "end_time")
	private String endTime;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "shift_type")
	private ShiftType shiftType;
	
	@Column
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date day;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "work_shift_waiters", joinColumns = @JoinColumn(name="work_shift_id"), inverseJoinColumns = @JoinColumn(name = "waiter_id"))	
	private List<Waiter> waiters;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "work_shift_cooks", joinColumns = @JoinColumn(name="work_shift_id"), inverseJoinColumns = @JoinColumn(name = "cook_id"))	
	private List<Cook> cooks;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "work_shift_bartenders", joinColumns = @JoinColumn(name="work_shift_id"), inverseJoinColumns = @JoinColumn(name = "bartender_id"))	
	private List<Bartender> bartenders;
	
	
	public WorkShift(){}

	public WorkShift(Long id, String startTime, String endTime, ShiftType shiftType, Date day, List<Waiter> waiters, List<Cook> cooks, List<Bartender> bartenders) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.shiftType = shiftType;
		this.day = day;
		this.waiters = waiters;
		this.cooks = cooks;
		this.bartenders = bartenders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ShiftType getShiftType() {
		return shiftType;
	}

	public void setShiftType(ShiftType shiftType) {
		this.shiftType = shiftType;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public List<Waiter> getWaiters() {
		return waiters;
	}

	public void setWaiters(List<Waiter> waiters) {
		this.waiters = waiters;
	}

	public List<Cook> getCooks() {
		return cooks;
	}

	public void setCooks(List<Cook> cooks) {
		this.cooks = cooks;
	}

	public List<Bartender> getBartenders() {
		return bartenders;
	}

	public void setBartenders(List<Bartender> bartenders) {
		this.bartenders = bartenders;
	}
	
	
	
	

	
}
