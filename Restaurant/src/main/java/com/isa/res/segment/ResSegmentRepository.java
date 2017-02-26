package com.isa.res.segment;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResSegmentRepository extends PagingAndSortingRepository<ResSegment, Long> {
	public ResSegment findBySegType(String segType);
}
