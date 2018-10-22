package com.barclays.theater.util;

public enum TheaterConstants {
	INPUT_FILE_NAME("theater.txt"), CANNOT_HANDLE_PARTY_MSG(" Sorry, we can't handle your party."),
	SPLIT_PARTY_MSG(" Call to split party."), TOTAL_ROW("6"), SECTION(" Section "), ROW(" Row "),
	ERROR_WHILE_READING_INPUT_FILE("Sorry, error while reading input file"),
	ERROR_WHILE_CLOSING_INPUT_READER("Sorry, error while closing input reader"), SINGLE_SPACE(" ")
	;
	
	private TheaterConstants(String message) {
		this.message = message;
	}
	
	private String message;
	
	@Override
	public String toString() {
		return this.message;
	}

}
