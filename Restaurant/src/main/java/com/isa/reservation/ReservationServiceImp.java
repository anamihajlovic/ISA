package com.isa.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
