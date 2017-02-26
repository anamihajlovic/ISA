package com.isa.res.segment;

import java.util.List;

public interface ResSegmentService {
	List<ResSegment> findAll();

	ResSegment save(ResSegment segment);

	ResSegment findOne(Long id);

	void delete(Long id);
	
	void deleteAll();
	
	ResSegment findbySegType(String s);

}
