package com.isa.reservation;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.res.table.ResTable;
import com.isa.res.table.ResTableService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	private final ReservationService reservationService;
	private final ResTableService resTableService;
	
	@Autowired
	public ReservationController(final ReservationService reservationService, final ResTableService resTableService) {
		this.reservationService = reservationService;
		this.resTableService = resTableService;
	}
	
	@PostMapping(path = "/add")
	public Reservation addReservation(@RequestBody Reservation reservation) {
		
		
		try {
		reservationService.save(reservation);
		} catch (Exception e) {
			System.out.println("Neuspesno dodavanje rezervacije u bazu");
			return null;
		}
		
		String[] array = reservation.getTables().split(";");
		
		Long[] tablesId = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
		  tablesId[i] = Long.valueOf(array[i]);
		}
		
		for(Long id: tablesId) {
			ResTable table = resTableService.findOne(id);
			table.getReservations().add(reservation);
			try {
				resTableService.save(table);
			} catch (Exception e){
				System.out.println("Neuspesno dodavanje rezervacije u resTable.");
				reservationService.delete(reservation.getId());
				return null;
			}
		
		}
		

		int hours = Integer.parseInt(reservation.getStartTime().split(":")[0]);
		int minutes = Integer.parseInt(reservation.getStartTime().split(":")[1]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,hours);
		cal.set(Calendar.MINUTE,minutes);
		//cal.set(Calendar.SECOND,0);
		//cal.set(Calendar.MILLISECOND,0);

		Date st = cal.getTime();
		System.out.println("startTime cal" + cal.getTime());
		System.out.println("startTime " + st);
		
		hours = Integer.parseInt(reservation.getEndTime().split(":")[0]);
		minutes = Integer.parseInt(reservation.getEndTime().split(":")[1]);
		cal.set(Calendar.HOUR_OF_DAY,hours);
		cal.set(Calendar.MINUTE,minutes);
		
		Date et = cal.getTime();
		System.out.println("endTime cal" + cal.getTime());
		System.out.println("endTime " + et);
		
		System.out.println(st.before(et) + " " + st.after(et) + " " + et.after(st));

		return reservation;
	}
	
	@GetMapping(path = "getReservation/{id}")
	public Reservation getReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.findById(id);
		
		return reservation;
	}
	
	


}
