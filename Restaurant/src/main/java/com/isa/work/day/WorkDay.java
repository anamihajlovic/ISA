package com.isa.work.day;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import com.isa.work.shift.WorkShift;

import java.util.Date;
import java.util.List;
@Entity
@Table(name="work_days")
public class WorkDay {

	
	@Id
	@GeneratedValue
	@Column(name = "work_day_id")
	private Long id;
	
	@Column 
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date day;
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "work_shifts_in_work_days", joinColumns = @JoinColumn(name = "work_day_id"), inverseJoinColumns = @JoinColumn(name = "work_shift_id"))
	private List<WorkShift> workShifts; ;
	
	
	public WorkDay() {}


	public WorkDay(Long id, Date day, List<WorkShift> workShifts) {
		super();
		this.id = id;
		this.day = day;
		this.workShifts = workShifts;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDay() {
		return day;
	}


	public void setDay(Date day) {
		this.day = day;
	}


	public List<WorkShift> getWorkShifts() {
		return workShifts;
	}


	public void setWorkShifts(List<WorkShift> workShifts) {
		this.workShifts = workShifts;
	}


}