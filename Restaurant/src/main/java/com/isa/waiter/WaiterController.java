package com.isa.waiter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiters")
public class WaiterController {
	
	private HttpSession httpSession;
	private final WaiterService waiterService;
	
	@Autowired
	public WaiterController(final HttpSession httpSession, final WaiterService waiterService) {
		this.httpSession = httpSession;
		this.waiterService = waiterService;
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

}
