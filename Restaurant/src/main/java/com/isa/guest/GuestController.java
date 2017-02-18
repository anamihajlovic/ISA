package com.isa.guest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isa.user.Role;

@RestController
@RequestMapping("/guests")
public class GuestController {
	
	private HttpSession httpSession;
	private final GuestService guestService;
	private final JavaMailSender mailSender;
	
	@Autowired
	public GuestController(final HttpSession httpSession, final GuestService guestService, final JavaMailSender mailSender) {
		this.httpSession = httpSession;
		this.guestService = guestService;
		this.mailSender = mailSender;
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
		String randomString = UUID.randomUUID().toString().replaceAll("-", "");
		guest.setActivationCode(randomString);

		
		try{
			guestService.save(guest);
			response = "OK";
		} catch (Exception ex) {
			response = "FAILURE";
		}
		
		
		//slanje mejla ako je OK
		if (response.equals("OK")) {
			try {
				SimpleMailMessage email = new SimpleMailMessage();
				email.setFrom("isa.restaurants123@gmail.com");
				email.setTo(guest.getEmail());
				email.setSubject("Activation link for your account");
				email.setText("Please, click on your activation link and activate you account."
						+ "Activation link is: http://localhost:8080/#/activateAccount/" + randomString);
				
				mailSender.send(email);
			} catch (Exception ex) {
				System.out.println("Email nije poslat.");
				response = "FAILURE";
				guestService.delete(guest.getId());//ako je bilo problema sa slanjem mail-a, da obrise prethodno dodatog gosta
			}
			
			
		}
		
		System.out.println(response);
		return response; 
	}
	
	@PostMapping(path = "/activateAccount/{activationCode}")
	@ResponseStatus(HttpStatus.OK)
	public void activateGuest(@PathVariable String activationCode) {
		
		try {
			guestService.activateAccount(activationCode);
		} catch (Exception ex) {
			System.out.println("Greska prilikom aktiviranja naloga.");
		}
		
	}
	
	@PostMapping(path = "/updateProfile")
	public Guest updateProfile(@Valid @RequestBody Guest guest) {
		System.out.println("updateProfile " + guest.getId() + " " + guest.getFirstName() + " " + guest.getLastName());
		
		try {
			guestService.save(guest);
			httpSession.setAttribute("user", guest);
		} catch (Exception ex) {
			System.out.println("Greska prilikom updateProfile-a guest-a.");
			return null;
		}
		
		
		return guest;
	}
	
	@GetMapping(path = "/findFriends/{id}")
	public List<Guest> findFriends(@PathVariable Long id) {
		System.out.println("findFriends " + id);
		
	    List<Guest> guests = guestService.findAll();
	    
	    for(int i=0; i< guests.size(); i++)
	    	if (guests.get(i).getId() == id) {
	    		guests.remove(i);
	    		System.out.println("Uklonjen korisnik sa id-jem iz liste " + id);
	    	}
	    	
	    System.out.println("velicina liste prijatelja iz findFriends " + guests.size());
	    List<Guest> sorted = getSortedFriends(guests);
		return sorted;
	}
	
	private List<Guest> getSortedFriends(List<Guest> friends) {
		Collections.sort(friends, new Comparator<Guest>() {

			@Override
			public int compare(Guest g1, Guest g2) {
				return g1.getLastName().compareTo(g2.getLastName());
			}
		});
		
		return friends;
	}


}
