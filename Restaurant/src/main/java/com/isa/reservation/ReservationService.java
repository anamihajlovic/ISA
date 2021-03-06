package com.isa.reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
	
	Reservation save(Reservation reservation);
	
	void delete(Long id);
	
	List<Reservation> findByResIdAndDate(Long resId, Date date);
	
	Reservation findById(Long id);

	List<Reservation> findAll();
	
	List<Reservation> findByGuestId(Long guestId);
	
	List<Reservation> findAllByResId(Long id);





}
