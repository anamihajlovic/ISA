package com.isa.reservation;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
	
	@SuppressWarnings("unchecked")
	public Reservation save(Reservation reservation);
	
	public void delete(Long id);
	
	public List<Reservation> findByResIdAndDate(Long resId, Date date);
	
	public Reservation findById(Long id);
	
	public List<Reservation> findByGuestId(Long guestId);



}
