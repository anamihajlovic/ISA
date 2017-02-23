package com.isa.friendship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.guest.Guest;
import com.isa.guest.GuestService;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {
	
	private HttpSession httpSession;
	private final FriendshipService friendshipService;
	private final GuestService guestService;
	
	@Autowired
	public FriendshipController(final HttpSession httpSession, final FriendshipService friendshipService, final GuestService guestService) {
		this.httpSession = httpSession;
		this.friendshipService = friendshipService;
		this.guestService = guestService;
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
	
	@DeleteMapping(path="/deleteFriend/{guestId}/{friendId}")
	public String deleteFriend(@PathVariable Long guestId, @PathVariable Long friendId) {
		System.out.println("POgodjena metoda deleteFriend " + friendId + " za" + guestId);
		
		try {
			friendshipService.delete(guestId, friendId);
		} catch(Exception e) {
			System.out.println("Neuspesno brisanje prijatelja.");
			return "FAILURE";
		}
		
		return "OK";
	}
	
	@GetMapping(path="/getNumOfFriendRequest/{id}")
	public int getNumOfFriendRequest(@PathVariable Long id) {
		
		List<Friendship> list = friendshipService.findByReceiverIdAndStatus(id, Status.sent);
		
		System.out.println("broj zahteva za prijateljstvo korisnika sa id " + id + " je " + list.size());
		
		return list.size();
		
	}
	
	@GetMapping(path="/getFriendRequests/{id}")
	public List<Guest> getFriendRequests(@PathVariable Long id) {
		System.out.println("Pogodjena metoda getFriendRequests za " + id);
		List<Friendship> requests = friendshipService.findByReceiverIdAndStatus(id, Status.sent);
		List<Guest> friends = new ArrayList<Guest>();

		
		for (Friendship f : requests) {
			Guest friend = guestService.findById(f.getSenderId());
			friends.add(friend);
			
		}


		friends = getSortedFriends(friends);

		return friends;
	}


	
	@PutMapping(path="/confirm/{senderId}/{receiverId}")
	public String confirm(@PathVariable Long senderId, @PathVariable Long receiverId) {
		System.out.println("Pogodjena metoda confirm " + senderId + " za" + receiverId);
		
		Friendship friendship = friendshipService.getBySenderIdAndReceiverId(senderId, receiverId);
		friendship.setStatus(Status.accepted);
		
		try {
			friendshipService.save(friendship);
		} catch(Exception e) {
			System.out.println("Neuspesno prihvatanje zahteva za prijateljstvo.");
			return "FAILURE";
		}
		
		return "OK";
	}

	@DeleteMapping(path="/deleteRequest/{senderId}/{receiverId}")
	public String deleteRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
		System.out.println("POgodjena metoda deleteRequest " + senderId + " za" + receiverId);
		
		try {
			friendshipService.delete(senderId, receiverId);
		} catch(Exception e) {
			System.out.println("Neuspesno brisanje zahteva za prijateljstvo.");
			return "FAILURE";
		}
		
		return "OK";
	}


}
