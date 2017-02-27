package com.isa.reservation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class ReservationServiceImp implements ReservationService {
	
	private final ReservationRepository repository;
	
	
	@Autowired
	public ReservationServiceImp(final ReservationRepository repository) {
		this.repository = repository;
	}

	@Override
	public Reservation save(Reservation reservation) {
		return repository.save(reservation);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<Reservation> findByResIdAndDate(Long resId, Date date) {
		return Lists.newArrayList(repository.findByResIdAndDate(resId, date));
	}

}
