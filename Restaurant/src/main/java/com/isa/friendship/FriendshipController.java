package com.isa.friendship;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.guest.Guest;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {
	
	private HttpSession httpSession;
	private final FriendshipService friendshipService;
	
	@Autowired
	public FriendshipController(final HttpSession httpSession, final FriendshipService friendshipService) {
		this.httpSession = httpSession;
		this.friendshipService = friendshipService;
	}
	
	@PostMapping(path="/addFriend/{friendId}")
	public String addFriend(@RequestBody Long guestId, @PathVariable Long friendId) {
		System.out.println("POgodjena metoda addFriend " + friendId + " za" + guestId);
		Friendship friendship = new Friendship(guestId, friendId);
		
		try {
			friendshipService.save(friendship);
		} catch(Exception e) {
			System.out.println("Neuspesno dodavanje prijatelja.");
			return "FAILURE";
		}
		
		return "OK";
	}
	
	@PostMapping(path="/deleteFriend/{friendId}")
	public String deleteFriend(@RequestBody Long guestId, @PathVariable Long friendId) {
		System.out.println("POgodjena metoda deleteFriend " + friendId + " za" + guestId);
		
		try {
			friendshipService.delete(guestId, friendId);
		} catch(Exception e) {
			System.out.println("Neuspesno brisanje prijatelja.");
			return "FAILURE";
		}
		
		return "OK";
	}


}
