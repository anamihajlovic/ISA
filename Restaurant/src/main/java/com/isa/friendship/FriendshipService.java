package com.isa.friendship;

import java.util.List;

public interface FriendshipService {
		
	List<Friendship> findBySenderIdOrReceiverId(Long guestId, Long guestId2);
	
	Friendship save(Friendship friendship);
	
	void delete(Long guestId, Long friendId);
	
	Friendship getBySenderIdAndReceiverId(Long guestId, Long friendId);




}
