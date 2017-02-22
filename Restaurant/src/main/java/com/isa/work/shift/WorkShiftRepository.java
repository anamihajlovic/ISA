package com.isa.work.shift;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorkShiftRepository extends PagingAndSortingRepository<WorkShift, Long>{ 

	public WorkShift findOne(Long id);

}




