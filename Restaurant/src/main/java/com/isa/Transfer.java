package com.isa;

public class Transfer {
	
	private String date;
	
	private Integer shift;
	
	private String cooks;
	
	private String bartenders;
	
	private String firstReon;
	
	private String secondReon;
	
	Transfer(){}

	public Transfer(String date, Integer shift, String cooks, String bartenders, String firstReon, String secondReon) {
		super();
		this.date = date;
		this.shift = shift;
		this.cooks = cooks;
		this.bartenders = bartenders;
		this.firstReon = firstReon;
		this.secondReon = secondReon;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getShift() {
		return shift;
	}

	public void setShift(Integer shift) {
		this.shift = shift;
	}

	public String getCooks() {
		return cooks;
	}

	public void setCooks(String cooks) {
		this.cooks = cooks;
	}

	public String getBartenders() {
		return bartenders;
	}

	public void setBartenders(String bartenders) {
		this.bartenders = bartenders;
	}

	public String getFirstReon() {
		return firstReon;
	}

	public void setFirstReon(String firstReon) {
		this.firstReon = firstReon;
	}

	public String getSecondReon() {
		return secondReon;
	}

	public void setSecondReon(String secondReon) {
		this.secondReon = secondReon;
	}

	
	

}
