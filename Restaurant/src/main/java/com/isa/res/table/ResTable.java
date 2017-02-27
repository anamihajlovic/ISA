package com.isa.res.table;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.isa.reservation.Reservation;

@Entity
@Table(name="tables")
public class ResTable {
	
	@Id
	@GeneratedValue
	@Column(name = "table_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name = "table_size")
	private SizeTable size;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name = "table_state")
	private StateTable state;
	
	@Column
	private String segment;
	
	@Column (name="segment_color")
	private String segColor; 
		
	@NotNull
	@Column
	private int xPos;
	
	@NotNull
	@Column 
	private int yPos;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "reservation_tables", joinColumns = @JoinColumn(name = "table_id"), inverseJoinColumns = @JoinColumn(name = "reservation_id"))
	private List<Reservation> reservations;
	
	public ResTable(){}

	public ResTable(Long id, SizeTable size, StateTable state, int xPos, int yPos, String segment, String segColor,  List<Reservation> reservations) {
		super();
		this.id = id;
		this.size = size;
		this.state = state;
		this.xPos = xPos;
		this.yPos = yPos;
		this.segment = segment;
		this.segColor = segColor;
		this.reservations = reservations;
	}

	
	public String getSegColor() {
		return segColor;
	}

	public void setSegColor(String segColor) {
		this.segColor = segColor;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SizeTable getSize() {
		return size;
	}

	public void setSize(SizeTable size) {
		this.size = size;
	}

	public StateTable getState() {
		return state;
	}

	public void setState(StateTable state) {
		this.state = state;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	
	
	
	
	

}
