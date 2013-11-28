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
	
	public int getNumberOfSessions(){
		return this.toString().equals("FULL") ? 2 : 1;
	}
	
	public boolean includesLunch(){
		if(this.toString().equals("FULL")){
			return true;
		}
		else{
			return this.toString().endsWith("WITHLUNCH");
		}
	}
}
