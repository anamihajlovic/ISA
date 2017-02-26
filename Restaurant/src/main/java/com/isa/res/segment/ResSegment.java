package com.isa.res.segment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.isa.res.table.ResTable;

@Entity
@Table(name="segments")
public class ResSegment {
	@Id
	@GeneratedValue
	@Column(name = "segment_id")
	private Long id;
	
	@NotNull
	@Column (name = "segment_type")
	private String segType;
	
	@NotNull 
	@Column(name= "color")
	private String color;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "segment_tables", joinColumns = @JoinColumn(name = "segment_id"), inverseJoinColumns = @JoinColumn(name = "table_id"))
	private List<ResTable> tables;

	public ResSegment(){}

	public ResSegment(Long id, String segType, List<ResTable> tables, String color) {
		super();
		this.id = id;
		this.segType = segType;
		this.tables = tables;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSegType() {
		return segType;
	}

	public void setSegType(String segType) {
		this.segType = segType;
	}

	public List<ResTable> getTables() {
		return tables;
	}

	public void setTables(List<ResTable> tables) {
		this.tables = tables;
	}
	
	
	
	

}

