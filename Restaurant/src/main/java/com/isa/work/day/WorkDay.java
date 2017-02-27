package com.isa.work.day;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.isa.bartender.Bartender;
import com.isa.cook.Cook;
import com.isa.employed.Employed;
import com.isa.user.Role;
import com.isa.waiter.Waiter;
import com.isa.work.shift.WorkShift;
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

	
	public WorkShift getCurrentWorkShift(Employed employee) {						
		System.out.println("bbbb");
		System.out.println(employee.getUserRole());
		
		if(employee.getUserRole().equals(Role.bartender)) {
			System.out.println("tu sam");
			for(WorkShift shift : this.getWorkShifts()) 
				if(shift.getBartenders() != null) {		
					System.out.println(employee.getEmail());
					for(Bartender bartender : shift.getBartenders())
						if(bartender.getEmail().equals(employee.getEmail()))
							if(checkTime(shift))
								return shift;
				}					
							
		} else if(employee.getUserRole().equals(Role.cook)) {
			
			for(WorkShift shift : this.getWorkShifts()) 
				if(shift.getCooks() != null) {		
					System.out.println(employee.getEmail());
					for(Cook cook : shift.getCooks())
						if(cook.getEmail().equals(employee.getEmail()))
							if(checkTime(shift))
								return shift;
				}					
		
		} else if(employee.getUserRole().equals(Role.waiter)) {
			
			for(WorkShift shift : this.getWorkShifts()) 
				if(shift.getWaiters() != null) {		
					System.out.println(employee.getEmail());
					for(Waiter waiter : shift.getWaiters())
						if(waiter.getEmail().equals(employee.getEmail()))
							if(checkTime(shift))
								return shift;
				}				
		}
								
		return null;
	}
	
	public boolean checkTime(WorkShift shift) {
		String timeFormat = "kk:mm:ss";

		Date now = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
		String currentStr = timeFormatter.format(now);	
		
		try{
			Date currentTime = timeFormatter.parse(currentStr);
			Date shiftStart = timeFormatter.parse(shift.getStartTime());
			Date shiftEnd = timeFormatter.parse(shift.getEndTime());
			
			System.out.println(currentTime);
			System.out.println(shiftStart);
			System.out.println(shiftEnd);
			
			if(!shiftStart.after(currentTime) && !shiftEnd.before(currentTime)) {
				System.out.println("Izmedju sam");
				return true;
			}
			return false;
		
		}catch(Exception e) {
			return false;
		}
		
		
		
		
		
	}
	
}