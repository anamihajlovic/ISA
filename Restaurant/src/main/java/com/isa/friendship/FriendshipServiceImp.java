package com.isa.friendship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

@Service
public class FriendshipServiceImp implements FriendshipService {
	
	private final FriendshipRepository repository;
	
	@Autowired
	public FriendshipServiceImp(final FriendshipRepository repository) {
		this.repository = repository;
	}


	@Override
	public List<Friendship> findBySenderIdOrReceiverId(Long guestId, Long guestId2) {
		return Lists.newArrayList(repository.findBySenderIdOrReceiverId(guestId, guestId2));
	}

	@Override
	public Friendship save(Friendship friendship) {
		return repository.save(friendship);
	}


	@Override
	public void delete(Long guestId, Long friendId) {
		Friendship fr = getBySenderIdAndReceiverId(guestId, friendId);
		
		if (fr == null)
			fr = getBySenderIdAndReceiverId(friendId, guestId);
		
		repository.delete(fr.getId());
		
	}


	@Override
	public Friendship getBySenderIdAndReceiverId(Long guestId, Long friendId) {
		return repository.getBySenderIdAndReceiverId(guestId, friendId);
	}

}
