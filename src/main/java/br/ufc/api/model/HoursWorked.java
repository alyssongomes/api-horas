package br.ufc.api.model;

import java.util.List;

public class HoursWorked {

	private Long hoursWorked;
	private Long minutesWorked;
	private List<HourQuery> hours;
	
	public Long getHoursWorked() {
		return hoursWorked;
	}
	
	public void setHoursWorked(Long hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	public Long getMinutesWorked() {
		return minutesWorked;
	}

	public void setMinutesWorked(Long minutesWorked) {
		this.minutesWorked = minutesWorked;
	}

	public List<HourQuery> getHours() {
		return hours;
	}
	
	public void setHours(List<HourQuery> hours) {
		this.hours = hours;
	}
	
}
