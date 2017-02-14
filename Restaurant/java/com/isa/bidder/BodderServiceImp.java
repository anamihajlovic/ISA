package com.isa.bidder;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.restaurant.RestaurantRepository;


@Service
@Transactional
public class BodderServiceImp implements BidderService {

	private final BidderRepository repository;
	private final RestaurantRepository repositoryRestaurant;

	@Autowired
	public BodderServiceImp(BidderRepository repository, RestaurantRepository repositoryRestaurant) {
		super();
		this.repository = repository;
		this.repositoryRestaurant = repositoryRestaurant;
	}

	@Override
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
	}

}
