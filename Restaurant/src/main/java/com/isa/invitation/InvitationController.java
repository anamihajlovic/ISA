package com.isa.invitation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.guest.Guest;
import com.isa.guest.GuestService;
import com.isa.reservation.Reservation;
import com.isa.reservation.ReservationService;

@RestController
@RequestMapping("/invitations")
public class InvitationController {
	
	private final InvitationService invitationService;
	private final ReservationService reservationService;
	private final GuestService guestService;
	private final JavaMailSender mailSender;
	
	@Autowired
	public InvitationController(final InvitationService invitationService, final ReservationService reservationService, final GuestService guestService, 
			final JavaMailSender mailSender) {
		this.invitationService = invitationService;
		this.reservationService = reservationService;
		this.guestService = guestService;
		this.mailSender = mailSender;
	}
	
	@PostMapping(path="/sendInvitation/{friendId}")
	public String sendInvitation(@RequestBody Long reservationId, @PathVariable Long friendId) {
		System.out.println("Pogodjena sendInvitation " + reservationId + " za prijatelja " + friendId);
		
		Reservation reservation = reservationService.findById(reservationId);
		Guest sender = guestService.findById(reservation.getGuestId());
		String senderName = sender.getFirstName() + " " + sender.getLastName();
		Guest friend = guestService.findById(friendId);
		String friendName = friend.getFirstName() + " " + friend.getLastName();
		
		Invitation invitation = new Invitation(reservationId, sender.getId(), friendId, friendName, InvitationStatus.pending);
		
		try {
			invitationService.save(invitation);
		} catch(Exception e) {
			System.out.println("Neuspesno cuvanje invitation-a.");
			return "FAILURE";
		}
		
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("isa.restaurants123@gmail.com");
			email.setTo(friend.getEmail());
			email.setSubject("Invitation for restaurant");
			email.setText("Your friend " + senderName + " invites you in restaurant. For more details and confirmation, please go on link " 
						+ "http://localhost:8080/#/confirmInvitation/" + invitation.getId() + "/" + reservation.getId());

			mailSender.send(email);
		} catch (Exception ex) {
			System.out.println("Email za invitation nije poslat.");
			invitationService.delete(invitation.getId());// ako je bilo problema sa slanjem mail-a, obrisi dodatog gosta
			return "FAILURE";
		}
		
		//ako budem imala listu invitationa u reservation-u, onda ovde dodati u tu listu kreiran invitation
		
		
		return "OK";
	}
	

	@PostMapping(path = "/confirmInvitation/{id}/{operation}")
	public Invitation confirmInvitation(@PathVariable Long id, @PathVariable String operation) {
		System.out.println("Pogodjena confirmInvitation " + id + " " + operation);
		
		Invitation invitation = invitationService.findById(id);
		
		if(operation.equals("accept"))
			invitation.setStatus(InvitationStatus.accepted);
		else
			invitation.setStatus(InvitationStatus.rejected);
		
		try {
			invitationService.save(invitation);
		} catch(Exception e) {
			System.out.println("Greska prilikom cuvanja izmena invitation.");
			return null;
		}
		
		return invitation;
	}
	
	@GetMapping(path = "/getInvitation/{id}")
	public Invitation getInvitation(@PathVariable Long id) {
		Invitation invitation = invitationService.findById(id);
		
		return invitation;
	}
	
	@GetMapping(path = "/getInvitedFriends/{reservationId}/{guestId}")
	public List<Invitation> getInvitedFriends(@PathVariable Long reservationId, @PathVariable Long guestId) {
		System.out.println("Pogodjena metoda getInvitedFriends ");
		ArrayList<Invitation> invitations = new ArrayList<Invitation>();
		
		Reservation reservation = reservationService.findById(reservationId);
		
		if (reservation.getGuestId() == guestId) {
			System.out.println("treba da prikazes prijatelje");
			invitations = (ArrayList<Invitation>)invitationService.findAllByReservationId(reservationId);

			if (invitations.size() == 0) {
				Invitation inv = new Invitation();
				inv.setFriendName("nema poziva");
				invitations.add(inv);
			}

		}
		else {
			invitations = null;
		}

		
		return invitations;
	}

}
