package com.isa.res.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.isa.system.manager.Preset;

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
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ReonTable reon;
	
	@Column (name="segment_color")
	private String segColor; 
		
	@NotNull
	@Column
	private int xPos;
	
	@NotNull
	@Column 
	private int yPos;
	
	public ResTable(){}

	public ResTable(Long id, SizeTable size, StateTable state, String segment, ReonTable reon, String segColor,
			int xPos, int yPos) {
		super();
		this.id = id;
		this.size = size;
		this.state = state;
		this.segment = segment;
		this.reon = reon;
		this.segColor = segColor;
		this.xPos = xPos;
		this.yPos = yPos;
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

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public ReonTable getReon() {
		return reon;
	}

	public void setReon(ReonTable reon) {
		this.reon = reon;
	}

	public String getSegColor() {
		return segColor;
	}

	public void setSegColor(String segColor) {
		this.segColor = segColor;
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

	
	
	
	

}
