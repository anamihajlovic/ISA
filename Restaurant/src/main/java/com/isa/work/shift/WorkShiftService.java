package com.isa.work.shift;

import java.util.List;

public interface WorkShiftService {

	List<WorkShift> findAll();

	WorkShift save(WorkShift workShift);

	WorkShift findOne(Long id);
	
	void delete(Long id);

}
