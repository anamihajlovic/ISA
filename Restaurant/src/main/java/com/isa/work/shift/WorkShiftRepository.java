package com.isa.work.shift;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorkShiftRepository extends PagingAndSortingRepository<WorkShift, Long>{ 

	public WorkShift findOne(Long id);
	
	public List<WorkShift> findByDay(Date day);

}




