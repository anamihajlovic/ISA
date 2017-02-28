package com.isa.invitation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Lists;

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

	@Override
	public List<Invitation> findAllByReservationIdAndStatus(Long id, InvitationStatus is) {
		// TODO Auto-generated method stub
		return Lists.newArrayList(repository.findAllByReservationIdAndStatus(id,is));
	}

	@Override
	public List<Invitation> findAllByReservationId(Long reservationId) {
		return Lists.newArrayList(repository.findAllByReservationId(reservationId));
	}

}
