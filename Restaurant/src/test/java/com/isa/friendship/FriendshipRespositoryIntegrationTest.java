package com.isa.friendship;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipRespositoryIntegrationTest {
	
	@Autowired
	FriendshipRepository repository;
	
	@Test
	public void checkFriendshipStatus() {
		
		Friendship fr = repository.getBySenderIdAndReceiverId(1L, 2L);
		assertThat(fr.getStatus()).isEqualTo(Status.accepted);
		
		fr = repository.getBySenderIdAndReceiverId(6L, 2L);
		assertThat(fr.getStatus()).isEqualTo(Status.sent);
		
	}
	
	@Test
	public void countFriendRequests() {
		
		List<Friendship> requests = repository.findByReceiverIdAndStatus(2L, Status.sent);
		assertThat(requests).hasSize(1);//da ima jedan zahteva za prijateljstvo
		
		requests = repository.findByReceiverIdAndStatus(1L, Status.sent);
		assertThat(requests.size()).isGreaterThan(0);//da ima zahteve za prijateljstvo

	}
	
	@Test
	public void countFriends() {
		List<Friendship> friendships = repository.findBySenderIdOrReceiverId(1l, 1L);
		
		int friendsCounter = 0;
		int requestCounter = 0;
		
		for (Friendship f: friendships) {
			if(f.getStatus().equals(Status.accepted))
				friendsCounter++;
			else
				requestCounter++;
		}
		
		assertThat(friendsCounter).isEqualTo(2);
		assertThat(requestCounter).isBetween(0, 5);
	}
	
	@Test
	public void sendFriendRequest() {
		
		Friendship friendship = new Friendship(4L, 3L);
		
		Friendship fr = repository.save(friendship);
		
		assertThat(fr.getId()).isEqualTo(9L);
		assertThat(fr.getStatus()).isEqualTo(Status.sent);
	}

}
