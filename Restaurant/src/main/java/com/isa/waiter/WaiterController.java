package com.isa.waiter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bartender.Bartender;

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
	
	@PutMapping(path = "/{id}")
	public void updateWaiter(@PathVariable Long id,@RequestBody Waiter waiter) {		
		waiterService.save(waiter);
	}

}
