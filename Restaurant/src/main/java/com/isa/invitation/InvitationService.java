package com.isa.invitation;

public interface InvitationService {
	
	Invitation save(Invitation invitation);
	
	void delete(Long id);
	
	Invitation findById(Long id);


}
