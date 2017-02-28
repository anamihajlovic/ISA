package com.isa;

public class Visit {
	private String date;
	
	private Integer number;
	
	public Visit(){}

	public Visit(String date, Integer number) {
		super();
		this.date = date;
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	

}
