package com.isa.invitation;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long> {
	
	@SuppressWarnings("unchecked")
	public Invitation save(Invitation invitation);
	
	public void delete(Long id);
	
	public Invitation findById(Long id);

	public List<Invitation> findAllByReservationIdAndStatus(Long id, InvitationStatus is);
	
	public List<Invitation> findAllByReservationId(Long reservationId);


}
