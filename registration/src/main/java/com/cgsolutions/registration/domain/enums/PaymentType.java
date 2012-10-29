package com.cgsolutions.registration.domain.enums;

public enum PaymentType {
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

}
