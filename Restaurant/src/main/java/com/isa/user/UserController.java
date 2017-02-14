package com.isa.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.guest.GuestService;
import com.isa.system.manager.SystemManagerService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private HttpSession httpSession;
	private final GuestService guestService;
	private final SystemManagerService systemManagerService;
	
	
	public UserController(final HttpSession httpSession, final GuestService guestService, final SystemManagerService systemManagerService) {
		this.httpSession = httpSession;
		this.guestService = guestService;
		this.systemManagerService = systemManagerService;
	}
	
	@PostMapping(path="/login")
	public User login(@RequestBody User userData) {
		System.out.println("Pogodjena metoda login " + userData.getEmail() + userData.getPassword());
		
		User user = new User("neuspesno@gmail.com", "neuspesno", "neuspesno","neuspesno", Role.guest, false);
		
		if (guestService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = guestService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
			
		} else if (systemManagerService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = systemManagerService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		}
		
		if (!user.getEmail().equals("neuspesno@gmail.com"))
			httpSession.setAttribute("user", user);
		
		return user;
	}

}
