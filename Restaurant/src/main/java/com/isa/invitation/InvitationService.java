package com.isa.invitation;

import java.util.List;

public interface InvitationService {
	
	Invitation save(Invitation invitation);
	
	void delete(Long id);
	
	Invitation findById(Long id);
	
	List<Invitation> findAllByReservationIdAndStatus(Long id,InvitationStatus is);


}
