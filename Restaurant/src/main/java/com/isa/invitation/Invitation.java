package com.isa.invitation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="invitations")
public class Invitation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="reservation_id", nullable=false)
	private Long reservationId;
	
	@Column(name="sender_id", nullable=false)
	private Long senderId;
	
	@Column(name="friend_id", nullable=false)
	private Long friendId;
	
	@Column(name="friend_name", nullable=false)
	private String friendName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private InvitationStatus status;
	
	public Invitation() {}
	
	public Invitation(Long reservationId, Long senderId, Long friendId, String friendName, InvitationStatus status) {
		this.reservationId = reservationId;
		this.senderId = senderId;
		this.friendId = friendId;
		this.friendName = friendName;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public InvitationStatus getStatus() {
		return status;
	}

	public void setStatus(InvitationStatus status) {
		this.status = status;
	}

	
	
	
	
}
