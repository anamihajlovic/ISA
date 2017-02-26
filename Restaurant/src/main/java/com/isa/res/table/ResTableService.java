package com.isa.res.table;

import java.util.List;


public interface ResTableService {
	List<ResTable> findAll();

	ResTable save(ResTable table);

	ResTable findOne(Long id);

	void delete(Long id);
	
	void deleteAll();
}
