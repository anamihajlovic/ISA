package com.isa.res.table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.reservation.Reservation;
import com.isa.reservation.ReservationService;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;

@RestController
@RequestMapping("/resTables")
public class ResTableController {

	private final ResTableService resTableService;
	private final RestaurantService restaurantService;
	private final ReservationService reservationService;

	@Autowired
	public ResTableController(final ResTableService resTableService, final RestaurantService restaurantService, final ReservationService reservationService) {
		this.resTableService = resTableService;
		this.restaurantService = restaurantService;
		this.reservationService = reservationService;
	}

	@GetMapping(path = "/getTables/{id}/{dateString}/{newST}/{newET}")
	public List<ResTable> getTables(@PathVariable Long id, @PathVariable String dateString, @PathVariable String newST, @PathVariable String newET) {
		System.out.println("Pogodjena metoda getTables " + id + " " + dateString + " " + newST + " " + newET);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e1) {
			System.out.println("Neuspesno parsiranje datuma.");
		}
		
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationService.findByResIdAndDate(id, date);
		ArrayList<Long> reservedTablesId = new ArrayList<Long>();
		
		Date newSTDate = convertTimeToDate(newST);
		Date newETDate = convertTimeToDate(newET);
		
		for (Reservation r: reservations) {
			Long[] ids = getTablesIds(r.getTables());
			Date tableSTDate = convertTimeToDate(r.getStartTime());
			Date tableETDate = convertTimeToDate(r.getEndTime());
			
			boolean reserved = isTableReserved(tableSTDate, tableETDate, newSTDate, newETDate);

			if (reserved) {
				for(Long i: ids)
					reservedTablesId.add(i);
			}
		}
		
		
		ArrayList<ResTable> outTables = new ArrayList<ResTable>();
		ArrayList<ResTable> resultTables = new ArrayList<ResTable>();
		int visina = 0;
		int sirina = 0;

		try {
			Restaurant r = restaurantService.findOne(id);
			for (int i = 0; i < r.getSegments().size(); i++) {
				outTables.addAll(r.getSegments().get(i).getTables());

			}

			if (visina == 0 || sirina == 0) {
				for (ResTable tt : outTables) {
					if (tt.getxPos() > visina) {
						visina = tt.getxPos();
					}
					if (tt.getyPos() > sirina) {
						sirina = tt.getyPos();
					}

				}
				visina = visina + 1;
				sirina = sirina + 1;

			}

			for (int i = 0; i < visina; i++) {
				for (int j = 0; j < sirina; j++) {
					for (ResTable rr : outTables) {
						if (rr.getxPos() == i && rr.getyPos() == j) {
							resultTables.add(rr);
						}

					}
				}
			}
			
			for(int i=0; i<resultTables.size(); i++) {
				if(reservedTablesId.contains(resultTables.get(i).getId())) {
					System.out.println("sto je rezervisan " + resultTables.get(i).getId());
					resultTables.get(i).setState(StateTable.reserved);
					System.out.println("stanje stola je sad " + resultTables.get(i).getState());

				}
			}


			return resultTables;
		} catch (Exception e) {
			return resultTables;
		}
	}
	
	
	@GetMapping(path = "/getTable/{id}")
	public ResTable getResTable(@PathVariable Long id) {
		System.out.println("Pogodjena metoda getResTable " + id);
		ResTable table = resTableService.findOne(id);
		
		return table;
		
	}
	
	private Long[] getTablesIds(String tables) {
		String[] array = tables.split(";");
		
		Long[] tablesId = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
		  tablesId[i] = Long.valueOf(array[i]);
		}
		
		return tablesId;
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
	
	private boolean isTableReserved(Date tableST, Date tableET, Date newST, Date newET) {
		
		if (tableST.compareTo(newET) < 0 && tableET.compareTo(newST) > 0)
			return true;
		else
			return false;
		
	}
	
	

}
