package com.isa.reservation;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	private final ReservationService reservationService;
	
	@Autowired
	public ReservationController(final ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping(path = "/add")
	public Reservation addReservation(@RequestBody Reservation reservation) {
		
		System.out.println("res id " + reservation.getResId());
		System.out.println("res name " + reservation.getResName());
		System.out.println("guest id " + reservation.getGuestId());
		System.out.println("date " + reservation.getDate());
		System.out.println("startTime " + reservation.getStartTime());
		System.out.println("endTime " + reservation.getEndTime());
		
		
		
		try {
		reservationService.save(reservation);
		} catch (Exception e) {
			System.out.println("Neuspesno dodavanje rezervacije u bazu");
			return null;
		}
		

		return reservation;
	}
	
	


}
