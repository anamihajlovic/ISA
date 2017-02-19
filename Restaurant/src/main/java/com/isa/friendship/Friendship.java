package com.isa.friendship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friendships")
public class Friendship {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="sender_id", nullable=false)
	private Long senderId;
	
	@Column(name="receiver_id", nullable=false)
	private Long receiverId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private Status status;
	
	public Friendship() {}
	
	public Friendship(Long senderId, Long receiverId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.status = Status.sent;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	
	

}
