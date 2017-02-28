package com.isa.reservation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		

		return reservation;
	}
	
	@GetMapping(path = "getReservation/{id}")
	public Reservation getReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.findById(id);
	
		return reservation;
	}
	
	@GetMapping(path = "/getMyVisits/{guestId}")
	public List<Reservation> getMyVisits(@PathVariable Long guestId) {
		System.out.println("Pogodjena metoda getHistory za gosta " + guestId);
		ArrayList<Reservation> reservations = (ArrayList<Reservation>)reservationService.findByGuestId(guestId);
		Date currentTime = convertTimeToDate(getCurrentTime());
		System.out.println("currentTime " + currentTime);
		Date currentDate = getCurrentDate();
		System.out.println("currentDate " + currentDate);
		
		ArrayList<Reservation> retVal = new ArrayList<Reservation>();
		
		for(Reservation r: reservations) {
			if(r.getDate().compareTo(currentDate) < 0) {//ako je datum pre danasnjeg
				retVal.add(r);
			} else if (r.getDate().compareTo(currentDate) == 0) {//ako je danasnji datum
				//Date sTime = convertTimeToDate(r.getStartTime());
				Date eTime = convertTimeToDate(r.getEndTime());
				
				if (eTime.compareTo(currentDate) <= 0)//dodaj samo ako se termin zavrsio
					retVal.add(r);
			}
		}
		
		return retVal;
		
	}
	

	
	
	private Date getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sDate= sdf.format(date);
		
		Date currentDate = new Date();
		
		try {
			currentDate = sdf.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return currentDate;
	}
	
	private String getCurrentTime() {
		Date now = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
		String currentTime = timeFormatter.format(now);
		return currentTime;
	}

	private Date convertTimeToDate(String time) {
		int hours = Integer.parseInt(time.split(":")[0]);
		int minutes = Integer.parseInt(time.split(":")[1]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,hours);
		cal.set(Calendar.MINUTE,minutes);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);

		Date timeDate = cal.getTime();
		//System.out.println("startTime cal" + cal.getTime());
		//System.out.println("startTime " + st);
		
		return timeDate;
	}

}
