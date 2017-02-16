package com.isa.guest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(path="/register")
	public String register(@Valid @RequestBody RegisterData guestData) {
		System.out.println("Pogodjena metoda register " + guestData.getFirstName() + " " + guestData.getEmail() + " " + guestData.getPassword());
		
		
		return "OK"; 
	}

}
