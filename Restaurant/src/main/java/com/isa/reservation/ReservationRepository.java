package com.isa.reservation;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
	
	@SuppressWarnings("unchecked")
	public Reservation save(Reservation reservation);

}
