package com.isa.work.shift;

import java.util.Date;
import java.util.List;

public interface WorkShiftService {

	List<WorkShift> findAll();

	WorkShift save(WorkShift workShift);

	WorkShift findOne(Long id);
	
	List<WorkShift> findByDay(Date day);
	
	void delete(Long id);

}
