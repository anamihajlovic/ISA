package com.isa.invitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationServiceImp implements InvitationService {
	
	private final InvitationRepository repository;
	
	@Autowired
	public InvitationServiceImp(final InvitationRepository repository) {
		this.repository = repository;
	}

	public Invitation save(Invitation invitation) {
		return repository.save(invitation);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Invitation findById(Long id) {
		return repository.findById(id);
	}

}
