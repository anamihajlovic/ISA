package com.isa.reservation;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.invitation.Invitation;
import com.isa.invitation.InvitationService;
import com.isa.invitation.InvitationStatus;
import com.isa.res.table.ResTable;
import com.isa.res.table.ResTableService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	private final ReservationService reservationService;
	private final ResTableService resTableService;
	private final InvitationService invitationService;
	
	@Autowired
	public ReservationController(final ReservationService reservationService, final ResTableService resTableService, final InvitationService invitationService) {
		this.reservationService = reservationService;
		this.resTableService = resTableService;
		this.invitationService = invitationService;
	}
	
	@PostMapping(path = "/add")
	public Reservation addReservation(@RequestBody Reservation reservation) {
		
		reservation.setDeleted(false);
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
		System.out.println("Pogodjena metoda getMyVisits za gosta " + guestId);
		
		ArrayList<Reservation> reservations = (ArrayList<Reservation>)reservationService.findAll();
		Date currentTime = convertTimeToDate(getCurrentTime());
		Date currentDate = getCurrentDate();
		
		//Date curTime = getCurrentTimeDate();
		
		ArrayList<Reservation> retVal = new ArrayList<Reservation>();
		
		for(Reservation r: reservations) {
			if(r.getGuestId() == guestId && !r.getDeleted()) {
			if(r.getDate().compareTo(currentDate) < 0) {//ako je datum pre danasnjeg
				retVal.add(r);
			} else if (r.getDate().compareTo(currentDate) == 0) {//ako je danasnji datum
				Date eTime = convertTimeToDate(r.getEndTime());
				
				if (eTime.compareTo(currentTime) <= 0)//dodaj samo ako se termin zavrsio
					retVal.add(r);
			}
		}
		}
		//uzeti invitations za ovaj id i status accepted i onda preuzeti te rezervacije
		ArrayList<Invitation> invitations = (ArrayList<Invitation>) invitationService.findAll();
		
		for(Invitation i: invitations) {
			if (i.getFriendId() == guestId && i.getStatus().equals(InvitationStatus.accepted)) {
				Reservation res = reservationService.findById(i.getReservationId());
				
				if(!res.getDeleted()) {
					if(res.getDate().compareTo(currentDate) < 0) {//ako je datum pre danasnjeg
						retVal.add(res);
					} else if (res.getDate().compareTo(currentDate) == 0) {//ako je danasnji datum
						Date eTime = convertTimeToDate(res.getEndTime());
						if (eTime.compareTo(currentTime) <= 0)//dodaj samo ako se termin zavrsio
							retVal.add(res);
					}
				}
			}
		}
		
		
		return retVal;
		
	}
	
	@GetMapping(path = "/getActiveReservations/{guestId}")
	public List<Reservation> getActiveReservations(@PathVariable Long guestId) {
		System.out.println("Pogodjena getActiveReservations " + guestId);
		
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationService.findAll();
		Date currentDate = getCurrentDate();
		Date time30 = add30Minutes();
		
		ArrayList<Reservation> retVal = new ArrayList<Reservation>();
		
		for(Reservation r: reservations) {
			if(r.getGuestId() == guestId && !r.getDeleted()) {
			if (r.getDate().compareTo(currentDate) > 0) {
				retVal.add(r);
			} else if (r.getDate().compareTo(currentDate) == 0) {
				Date sTime = convertTimeToDate(r.getStartTime());
				
				if (time30.compareTo(sTime) <= 0)//dodaj samo ako je vreme 30min pre pocetka termina
					retVal.add(r);
			}
		}
		}
		
		if (retVal.size() == 0)
			retVal = null;

		return retVal;
	}
	
	@DeleteMapping("/deleteReservation/{id}")
	public String deleteReservation(@PathVariable Long id) {
		System.out.println("Pogodjena metoda deleteReservation " + id);
		
		Reservation reservation = reservationService.findById(id);
		reservation.setDeleted(true);
		
		try {
			reservationService.save(reservation);
		} catch (Exception e) {
			System.out.println("Neuspesno brisanje rezervacije");
			return "FAILURE";
		}
		
		return "OK";
		
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
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
		String currentTime = timeFormatter.format(now);
		return currentTime;
	}
	private Date getCurrentTimeDate() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    Calendar cal = Calendar.getInstance();
	    
	    return cal.getTime();
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
	
	private Date add30Minutes() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 30);
        
        return cal.getTime();
	}

}
