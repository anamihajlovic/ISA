package com.isa.grade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grade_id")
	private Long id;
	
	@Column(name = "guest_id", nullable = false)
	private Long guestId;
	
	@Column(name = "restaurant_id", nullable = false)
	private Long restaurantId;
	
	@Column(name = "reservation_id", nullable = false)
	private Long reservationId;
	
	@Column(name = "waiter_id", nullable = false)
	private Long waiterId;
	
	@Column(name = "order_id", nullable = false)
	private Long orderId;
	
	@Column(name = "order_grade", nullable = true)
	private Double orderGrade;
	
	@Column(name = "waiter_grade", nullable = true)
	private Double waiterGrade;
	
	@Column(name = "restaurant_grade", nullable = true)
	private Double restaurantGrade;
		
	@Temporal(TemporalType.DATE)
	@Column (name= "date", nullable=false)
	private Date date;
		
	
	
	public Grade() {}	


	public Grade(Long id, Long guestId, Long restaurantId, Long reservationId, Long waiterId, Long orderId,
			Double orderGrade, Double waiterGrade, Double restaurantGrade, Date date) {
		super();
		this.id = id;
		this.guestId = guestId;
		this.restaurantId = restaurantId;
		this.reservationId = reservationId;
		this.waiterId = waiterId;
		this.orderId = orderId;
		this.orderGrade = orderGrade;
		this.waiterGrade = waiterGrade;
		this.restaurantGrade = restaurantGrade;
		this.date = date;
	}







	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getOrderGrade() {
		return orderGrade;
	}

	public void setOrderGrade(Double orderGrade) {
		this.orderGrade = orderGrade;
	}

	public Double getWaiterGrade() {
		return waiterGrade;
	}

	public void setWaiterGrade(Double waiterGrade) {
		this.waiterGrade = waiterGrade;
	}

	public Double getRestaurantGrade() {
		return restaurantGrade;
	}

	public void setRestaurantGrade(Double restaurantGrade) {
		this.restaurantGrade = restaurantGrade;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}	
	
	
		
}
