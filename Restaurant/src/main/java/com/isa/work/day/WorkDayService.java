package com.isa.work.day;

import java.util.List;

import com.isa.work.shift.WorkShift;

public interface WorkDayService {
	
	List<WorkDay> findAll();

	WorkDay save(WorkDay workDay);

	WorkDay findOne(Long id);

}
