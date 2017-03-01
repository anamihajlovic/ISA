package com.isa.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class GradeServiceImp implements GradeService {
	
	private final GradeRepository repository;
	
	
	@Autowired
	public GradeServiceImp(GradeRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Grade save(Grade grade) {
		return repository.save(grade);
	}

	@Override
	public List<Grade> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Grade findByOrderId(Long id) {
		// TODO Auto-generated method stub
		return repository.findByOrderId(id);
	}

	@Override
	public Grade findByGuestIdAndReservationId(Long guestId, Long reservationId) {
		// TODO Auto-generated method stub
		return repository.findByGuestIdAndReservationId(guestId, reservationId);
	}
	
	
}
