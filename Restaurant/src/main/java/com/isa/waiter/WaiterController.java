package com.isa.waiter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

}
