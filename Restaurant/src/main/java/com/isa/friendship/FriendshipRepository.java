package com.isa.friendship;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FriendshipRepository extends PagingAndSortingRepository<Friendship, Long> {
	
	//vrati sva prijateljstva u kojima se id gosta pojavljuje ili kao sender ili kao receiver
	public List<Friendship> findBySenderIdOrReceiverId(Long guestId, Long guestId2);
	
	@SuppressWarnings("unchecked")
	public Friendship save(Friendship friendship);
	
	public void delete(Long id);
	
	public Friendship getBySenderIdAndReceiverId(Long guestId, Long friendId);


}
