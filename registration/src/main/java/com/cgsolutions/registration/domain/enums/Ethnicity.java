package com.cgsolutions.registration.domain.enums;

public enum Ethnicity {
	WHB("White British"),
	WHR("White Irish"),
	MWB("White/Black Caribbean"),
	AIN("Indian"),
	MBA("White/Black African"),
	APK("Pakistani"),
	WHT("Traveller of Irish Heritage"),
	MWA("White and Asain"),
	ABA("Bangladeshi"),
	WRO("Gypsy/Roma"),
	MOT("Any other mixed background"),
	AAO("Any other Asain background"),
	WHA("Any other White background"),
	BLB("Black Caribbean"),
	CHE("Chinese"),
	REF("Refuse to provide"),
	BLF("Blac African"),
	OEO("Any other background"),
	NOT("Info not obtained"),
	BLG("Any other Black background");
	
	private Ethnicity(String description) {
		this.description = description;
	}
	
	private final String description;
	
	public String getDescription() {
		return description;
	}
}
