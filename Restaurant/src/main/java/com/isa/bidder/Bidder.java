package com.isa.bidder;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.offer.Offer;
import com.isa.res.order.ResOrder;
import com.isa.user.User;

@Entity
@Table(name="bidders")
public class Bidder extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bidder_id")
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "bidder_offers", joinColumns = @JoinColumn(name = "bidder_id"), inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private List<Offer> offers;
	
	public Bidder() {}

	public Bidder(Long id, List<Offer> offers) {
		super();
		this.id = id;
		this.offers = offers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	
	
	
	

}
