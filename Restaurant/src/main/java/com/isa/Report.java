package com.isa;

import java.util.List;

import com.isa.waiter.Waiter;

public class Report {
	
	private String dateReport;
	
	private Long  waiter;
	
	private Long income;
	
	private String waiterName;
	
	public Report(){}

	public Report(String dateReport, Long waiter, Long income, String waiterName) {
		super();
		this.dateReport = dateReport;
		this.waiter = waiter;
		this.income = income;
		this.waiterName = waiterName;
	}

	public String getDateReport() {
		return dateReport;
	}

	public void setDateReport(String dateReport) {
		this.dateReport = dateReport;
	}

	public Long getWaiter() {
		return waiter;
	}

	public void setWaiter(Long waiter) {
		this.waiter = waiter;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}

	

}
