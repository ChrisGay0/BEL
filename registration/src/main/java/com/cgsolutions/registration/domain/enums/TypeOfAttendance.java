package com.cgsolutions.registration.domain.enums;

public enum TypeOfAttendance {
	MORNING("Morning"),
	MORNINGWITHLUNCH("Morning with lunch"),
	AFTERNOON("Afternoon"),
	AFTERNOONWITHLUNCH("Afternoon with lunch"),
	FULL("Full Day");
	
	private TypeOfAttendance(String description) {
		this.description = description;
	}
	
	private final String description;
	
	public String getDescription() {
		return description;
	}
}
