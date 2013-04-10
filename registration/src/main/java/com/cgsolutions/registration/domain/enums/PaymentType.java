package com.cgsolutions.registration.domain.enums;

public enum PaymentType {
	BACS("Bacs"),
	CASH("Cash"),
	CHEQUE("Cheque"),
	CARD("Card");
	
	private PaymentType(String description) {
		this.description = description;
	}
	
	private final String description;
	
	public String getDescription() {
		return description;
	}
	
	public String getStringValue(){
		return this.toString();
	}

}
