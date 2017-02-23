package com.isa.guest;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isa.friendship.Friendship;
import com.isa.friendship.FriendshipService;
import com.isa.friendship.Status;
import com.isa.user.Role;

@RestController
@RequestMapping("/guests")
public class GuestController {

	private HttpSession httpSession;
	private final GuestService guestService;
	private final JavaMailSender mailSender;
	private final FriendshipService friendshipService;

	@Autowired
	public GuestController(final HttpSession httpSession, final GuestService guestService,
			final JavaMailSender mailSender, final FriendshipService friendshipService) {
		this.httpSession = httpSession;
		this.guestService = guestService;
		this.mailSender = mailSender;
		this.friendshipService = friendshipService;
	}

	@PostMapping(path = "/register")
	public String register(@Valid @RequestBody RegisterData guestData, BindingResult bindingResult) {
		System.out.println("Pogodjena metoda register " + guestData.getFirstName() + " " + guestData.getEmail() + " "
				+ guestData.getPassword());

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
			if (bindingResult.hasFieldErrors("confirm"))
				response += "confirm";
			return response;
		}

		if (!guestData.getPassword().equals(guestData.getConfirm()))								
			return "match";

		Guest guest = new Guest();
		guest.setFirstName(guestData.getFirstName());
		guest.setLastName(guestData.getLastName());
		guest.setEmail(guestData.getEmail());
		guest.setPassword(guestData.getPassword());
		guest.setUserRole(Role.guest);
		guest.setActive(false);
		guest.setFirstLogIn(false);
		String randomString = UUID.randomUUID().toString().replaceAll("-", "");
		guest.setActivationCode(randomString);

		try {
			guestService.save(guest);
		} catch (Exception ex) {
			System.out.println("Greska prilikom dodavanja registrovanog gosta u bazu.");
			return "FAILURE";
		}


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
			guestService.delete(guest.getId());// ako je bilo problema sa slanjem mail-a, obrisi dodatog gosta
			return "FAILURE";
		}

		
		return "OK";
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
	
	@PutMapping(path = "/changePassword")
	public Guest changePassword(@RequestBody Guest guest) {
		
		/*if(guest.getFirstLogIn())
			guest.setFirstLogIn(false);*/
		
		try{
			guestService.save(guest);
			httpSession.setAttribute("user", guest);
		} catch(Exception e) {
			System.out.println("Greska pri promeni sifre gosta");
			return null;
		}
		
		return guest;	
	}

	@GetMapping(path = "/findFriends/{id}")
	public List<Guest> findFriends(@PathVariable Long id) {
		System.out.println("findFriends " + id);

		List<Guest> guests = guestService.findAll();
		List<Friendship> friendships = friendshipService.findBySenderIdOrReceiverId(id, id);

		for (int i = 0; i < guests.size(); i++)
			if (guests.get(i).getId() == id) {// uklanjanje gosta koji trazi
												// prijatelje
				guests.remove(i);
				System.out.println("Uklonjen korisnik sa id-jem iz liste " + id);
			}

		for (Friendship f : friendships) {
			for (int i = 0; i < guests.size(); i++)
				if (guests.get(i).getId() == f.getReceiverId() || guests.get(i).getId() == f.getSenderId()) {// brisanje
																												// onih
																												// sa
																												// kojima
																												// je
																												// vec
																												// prijatelj
																												// ili
																												// je
																												// status
																												// send
					guests.remove(i);

				}
		}

		System.out.println("velicina liste prijatelja iz findFriends " + guests.size());
		List<Guest> sorted = getSortedFriends(guests);
		return sorted;
	}

	@GetMapping(path = "/myFriends/{id}")
	public List<Guest> getMyFriends(@PathVariable Long id) {
		List<Guest> myFriends = new ArrayList<Guest>();
		List<Long> myFriendsId = new ArrayList<Long>();

		List<Friendship> friendships = friendshipService.findBySenderIdOrReceiverId(id, id);

		for (Friendship f : friendships) {
			if (f.getStatus().equals(Status.accepted)) {
				if (f.getSenderId() == id)
					myFriendsId.add(f.getReceiverId());
				else if (f.getReceiverId() == id)
					myFriendsId.add(f.getSenderId());
			}
		}

		for (Long i : myFriendsId) {
			Guest friend = guestService.findById(i);
			myFriends.add(friend);
		}

		myFriends = getSortedFriends(myFriends);

		return myFriends;
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
