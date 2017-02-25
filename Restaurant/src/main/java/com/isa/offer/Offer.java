package com.isa.offer;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.isa.offer.unit.OfferUnit;

@Entity
@Table(name="offers")
public class Offer {
	@Id
	@GeneratedValue
	@Column(name = "offer_id")
	private Long id;
	
	@Column(name = "id_bidder")
	private Long idBidder;
	
	@Column(name = "id_res_order")
	private Long idResOrder;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "accepted")
	private StateOffer accepted;
	
	@Column (name= "delivery_time")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date deliveryTime;
	
	@Column
	@NotNull
	private String garancy;
	
	@Column
	private String seen;
	
	@Column (name="total_price")
	@Min(0)
	private Long totalPrice;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "units", joinColumns = @JoinColumn(name = "offer_id"), inverseJoinColumns = @JoinColumn(name = "unit_id"))
	private List<OfferUnit> offerUnits;

	public Offer(){}

	public Offer(Long id, Long idBidder, Long idResOrder, StateOffer accepted, Date deliveryTime, String garancy,
			String seen, Long totalPrice, List<OfferUnit> offerUnits) {
		super();
		this.id = id;
		this.idBidder = idBidder;
		this.idResOrder = idResOrder;
		this.accepted = accepted;
		this.deliveryTime = deliveryTime;
		this.garancy = garancy;
		this.seen = seen;
		this.totalPrice = totalPrice;
		this.offerUnits = offerUnits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdBidder() {
		return idBidder;
	}

	public void setIdBidder(Long idBidder) {
		this.idBidder = idBidder;
	}

	public Long getIdResOrder() {
		return idResOrder;
	}

	public void setIdResOrder(Long idResOrder) {
		this.idResOrder = idResOrder;
	}

	public StateOffer getAccepted() {
		return accepted;
	}

	public void setAccepted(StateOffer accepted) {
		this.accepted = accepted;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getGarancy() {
		return garancy;
	}

	public void setGarancy(String garancy) {
		this.garancy = garancy;
	}

	public String getSeen() {
		return seen;
	}

	public void setSeen(String seen) {
		this.seen = seen;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OfferUnit> getOfferUnits() {
		return offerUnits;
	}

	public void setOfferUnits(List<OfferUnit> offerUnits) {
		this.offerUnits = offerUnits;
	}

	

}
