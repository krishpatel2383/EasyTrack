package com.krish.EasyTrack.Model;

import java.time.LocalTime;

public class Report {

	private int totalDaysWorked;

	private String totalHoursWorked;

	private LocalTime avgCheckInTime;

	private LocalTime avgCheckOuTime;

	public int getTotalDaysWorked() {
		return totalDaysWorked;
	}

	public Report(int totalDaysWorked, String totalHoursWorked, LocalTime avgCheckInTime, LocalTime avgCheckOuTime) {
		super();
		this.totalDaysWorked = totalDaysWorked;
		this.totalHoursWorked = totalHoursWorked;
		this.avgCheckInTime = avgCheckInTime;
		this.avgCheckOuTime = avgCheckOuTime;
	}

	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setTotalDaysWorked(int totalDaysWorked) {
		this.totalDaysWorked = totalDaysWorked;
	}

	public String getTotalHoursWorked() {
		return totalHoursWorked;
	}

	public void setTotalHoursWorked(String totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}

	public LocalTime getAvgCheckInTime() {
		return avgCheckInTime;
	}

	public void setAvgCheckInTime(LocalTime avgCheckInTime) {
		this.avgCheckInTime = avgCheckInTime;
	}

	public LocalTime getAvgCheckOuTime() {
		return avgCheckOuTime;
	}

	public void setAvgCheckOuTime(LocalTime avgCheckOuTime) {
		this.avgCheckOuTime = avgCheckOuTime;
	}

}
