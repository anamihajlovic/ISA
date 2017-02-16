package com.isa.guest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.user.Role;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
	public String register(@Valid @RequestBody RegisterData guestData, BindingResult bindingResult) {
		System.out.println("Pogodjena metoda register " + guestData.getFirstName() + " " + guestData.getEmail() + " " + guestData.getPassword());
		
		String response = "";
		if (bindingResult.hasErrors()) {
			if (bindingResult.hasFieldErrors("firstName"))
				response += "first";
			if (bindingResult.hasFieldErrors("lastName"))
				response += "last";
			if (bindingResult.hasFieldErrors("email")) 
				response += "email";
			if (bindingResult.hasFieldErrors("password"))
				response += "password";
			if(bindingResult.hasFieldErrors("confirm"))
				response += "confirm";
			return response;
		}
		
		if (!guestData.getPassword().equals(guestData.getConfirm()))//?da li ce nekad puci?
			return "match";
		
		Guest guest = new Guest();
		guest.setFirstName(guestData.getFirstName());
		guest.setLastName(guestData.getLastName());
		guest.setEmail(guestData.getEmail());
		guest.setPassword(guestData.getPassword());
		guest.setUserRole(Role.guest);
		guest.setActive(false);
		
		try{
			guestService.save(guest);
			response = "OK";
		} catch (Exception ex) {
			response = "FAILURE";
		}
		
		
//		if (dbGuest != null)
//			response = "OK";
//		else
//			response = "FAILURE";
		
		//slanje mejla ako je OK
		
		System.out.println(response);
		return response; 
	}

}
