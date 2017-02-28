package com.isa.waiter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.res.table.ResTable;
import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;
import com.isa.work.day.WorkDay;
import com.isa.work.shift.WorkShift;

@RestController
@RequestMapping("/waiters")
public class WaiterController {
	
	private HttpSession httpSession;
	private final WaiterService waiterService;
	private final RestaurantService restaurantService;
	private int visina = 0;
	private int sirina = 0 ;
	
	@Autowired
	public WaiterController(final HttpSession httpSession, final WaiterService waiterService, final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.waiterService = waiterService;
		this.restaurantService = restaurantService;
	}
	
	@PutMapping(path = "/updateInfo")
	public Waiter updateWaiter(@RequestBody Waiter waiter) {		
		try{
			waiterService.save(waiter);
			httpSession.setAttribute("user", waiter);
		} catch(Exception e) {
			System.out.println("Greska pri update-u konobara.");
			return null;
		}
		
		return waiter;					
	}
	
	@GetMapping(path = "/getWaiter/{waiterId}")
	public Waiter getWaiter(@PathVariable Long waiterId) {
		
		try{
			Waiter waiter = waiterService.findOne(waiterId);
			return waiter;
		} catch(Exception e){
			return null;
		}		
	}
	
	@PutMapping(path = "/changePassword")
	public Waiter changeWaiterPassword(@RequestBody Waiter waiter) {
		
		if(waiter.getFirstLogIn())
			waiter.setFirstLogIn(false);
		
		try{
			waiterService.save(waiter);
			httpSession.setAttribute("user", waiter);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre konobara");
			return null;
		}
		
		return waiter;	
	}
	
	@GetMapping(path = "/getSchedule/{id}")
	public List<WorkShift> getSchedule(@PathVariable Long id) {
		
		Waiter waiter= waiterService.findOne(id);
		Long restaurantId = waiter.getRestaurantId();		
		Restaurant restaurant = restaurantService.findOne(restaurantId);
							
		List<WorkShift> waiterShifts= new ArrayList<WorkShift>();
		
		for(WorkDay day : restaurant.getWorkDays()) {
			
			for(WorkShift shift : day.getWorkShifts()) {
				if(shift.getWaiters().size() != 0)
					waiterShifts.add(shift);
			}
		
		}
		
		return waiterShifts;		
	}
	
	@GetMapping(path = "/getWorkShift")
	public WorkShift getWorkShift() {
		
		try{
			Waiter waiter = (Waiter) httpSession.getAttribute("user");
			Restaurant restaurant = restaurantService.findOne(waiter.getRestaurantId());
			
			Date today = new Date();			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String todayStr = formatter.format(today);	
									
			System.out.println(today);
			
			for(WorkDay day : restaurant.getWorkDays()) {					
				String workDayStr = formatter.format(day.getDay());	
				System.out.println(todayStr.equals(workDayStr));
				if(todayStr.equals(workDayStr)) 
					return (day.getCurrentWorkShift(waiter));
					
			}
						
			return null;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}						
	}
	
	@GetMapping(path="/getTables")
	public List<ResTable> getTables(){
		ArrayList<ResTable> outTables = new ArrayList<ResTable>();
		ArrayList<ResTable> resultTables = new ArrayList<ResTable>();
		
		try {
			Waiter waiter = (Waiter)httpSession.getAttribute("user");
			Restaurant r = restaurantService.findOne(waiter.getRestaurantId());		
			
			for(int i=0; i<r.getSegments().size(); i++)
				outTables.addAll(r.getSegments().get(i).getTables());

			if(visina==0 || sirina==0){
				for(ResTable tt : outTables){
					if(tt.getxPos()>visina){
						visina = tt.getxPos();
					}
					if(tt.getyPos()>sirina){
						sirina = tt.getyPos();
					}
					
				}
				visina = visina + 1;
				sirina = sirina + 1;
				
			}
			
			for(int i =0;i<visina ;i++){
				for (int j=0;j<sirina;j++){
					for(ResTable rr : outTables)
						if(rr.getxPos()==i && rr.getyPos()==j)
							resultTables.add(rr);
				}
			}
			System.out.println("Uspeeeeh");
			return resultTables;
		
		} catch (Exception e) {			
			return resultTables;
		}
	}

}
