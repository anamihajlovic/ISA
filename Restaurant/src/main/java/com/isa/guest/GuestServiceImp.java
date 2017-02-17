package com.isa.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImp implements GuestService {
	
	private final GuestRepository repository;
	
	@Autowired
	public GuestServiceImp(final GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public Guest findByEMail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Guest findByEMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public Guest save(Guest guest) {
		return repository.save(guest);
	}

	@Override
	public void activateAccount(String activationCode) {
		Guest guest = repository.findByActivationCode(activationCode);
		guest.setActive(true);
		repository.save(guest);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
	
	

}
