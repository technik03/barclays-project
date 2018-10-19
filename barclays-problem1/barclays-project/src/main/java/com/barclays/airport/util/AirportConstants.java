package com.barclays.airport.util;

public enum AirportConstants {

	INPUT_DATA_SECTION_HEAD("# Section:"),
	FLIGHT_ARRIVAL("ARRIVAL"),
	DEST_BAGGAGE_CLAIM("BaggageClaim"),
	SINGLE_WHITE_SPACE(" ")
	;
	
	private String message;
	
	private AirportConstants(String message){
		this.message = message;
	}
	
	@Override
	public String toString(){
		return message;
	}
	
}
