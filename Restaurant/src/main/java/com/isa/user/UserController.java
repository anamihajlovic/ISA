package com.isa.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.bartender.BartenderService;
import com.isa.bidder.BidderService;
import com.isa.cook.CookService;
import com.isa.guest.GuestService;
import com.isa.res.manager.RestaurantManagerService;
import com.isa.system.manager.SystemManagerService;
import com.isa.waiter.WaiterService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private HttpSession httpSession;
	private final GuestService guestService;
	private final SystemManagerService systemManagerService;
	private final RestaurantManagerService restaurantManagerService;
	private final WaiterService waiterService;
	private final CookService cookService;
	private final BartenderService bartenderService;
	private final BidderService bidderService;
	
	@Autowired
	public UserController(final HttpSession httpSession, final GuestService guestService, final SystemManagerService systemManagerService
			, final RestaurantManagerService restaurantManagerService,final WaiterService waiterService,final CookService cookService
			, final BartenderService bartenderService, final BidderService bidderService) {
		this.httpSession = httpSession;
		this.guestService = guestService;
		this.systemManagerService = systemManagerService;
		this.restaurantManagerService = restaurantManagerService;
		this.waiterService = waiterService;
		this.cookService = cookService;
		this.bartenderService = bartenderService;
		this.bidderService = bidderService;
	}
	
	@PostMapping(path="/login")
	public User login(@Valid @RequestBody LoginData userData, BindingResult bindingResult) {
		System.out.println("Pogodjena metoda login " + userData.getEmail() + userData.getPassword());

		String errors = "";
		if (bindingResult.hasErrors()) {
			if (bindingResult.hasFieldErrors("email")) 
				errors += "email";
			if (bindingResult.hasFieldErrors("password"))
				errors += "password";
			return new User("neuspesno@gmail.com", errors, "neuspesno","neuspesno", Role.guest);
		}
		
		
		User user = new User("neuspesno@gmail.com", "neuspesno", "neuspesno","neuspesno", Role.guest);
		
		if (guestService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = guestService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
			
		} else if (systemManagerService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = systemManagerService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		} else if (restaurantManagerService.findByMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = restaurantManagerService.findByMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		} else if (waiterService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = waiterService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		} else if (cookService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = cookService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		} else if (bartenderService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = bartenderService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		} else if (bidderService.findByEMailAndPassword(userData.getEmail(), userData.getPassword()) != null) {
			user = bidderService.findByEMailAndPassword(userData.getEmail(), userData.getPassword());
			System.out.println("Baza vratila " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName()
			+ " " + user.getUserRole());
		}
		
		if (!user.getEmail().equals("neuspesno@gmail.com"))
			httpSession.setAttribute("user", user);
		
		System.out.println("user " + user.getEmail());
		return user;
	}
	
	@GetMapping(path="/logout")
	public String logout() {
		System.out.println("Logout");
		
		httpSession.invalidate();
		
		return "OK";
	}

}
