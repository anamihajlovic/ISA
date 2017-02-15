package com.isa.bidder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.restaurant.RestaurantRepository;


@Service
public class BidderServiceImp implements BidderService {

	private final BidderRepository repository;
	private final RestaurantRepository repositoryRestaurant;

	@Autowired
	public BidderServiceImp(BidderRepository repository, RestaurantRepository repositoryRestaurant) {
		this.repository = repository;
		this.repositoryRestaurant = repositoryRestaurant;
	}

	@Override
	public Bidder findByEMail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Bidder findByEMailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
	

/*	@Override
	public List<Bidder> findAll() {
		return (List<Bidder>) (repository.findAll());
	}

	@Override
	public Bidder save(Bidder bidder) {
		return repository.save(bidder);
	}

	@Override
	public Bidder findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Bidder findOne(String email, String password) {
		return repository.findByEmailAndPassword(email,password);
	}

	@Override
	public Bidder findOneWithMail(String mail) {
		return repository.findByEmail(mail);
	}*/

}
