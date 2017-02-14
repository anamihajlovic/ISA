package com.isa.guest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestController {
	
	private HttpSession httpSession;
	private final GuestService guestService;
	
	@Autowired
	public GuestController(final HttpSession httpSession, final GuestService guestService) {
		this.httpSession = httpSession;
		this.guestService = guestService;
	}

}
