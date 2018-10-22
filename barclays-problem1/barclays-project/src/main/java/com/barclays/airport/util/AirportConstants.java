/**
 * Enum: Global access class for constants used in this project
 * @author niharika
 */
package com.barclays.airport.util;

public enum AirportConstants {

	INPUT_DATA_SECTION_HEAD("# Section:"),
	FLIGHT_ARRIVAL("ARRIVAL"),
	DEST_BAGGAGE_CLAIM("BaggageClaim"),
	SINGLE_WHITE_SPACE(" "),
	ILLEGAL_ARGS_OR_INPUT_REFER_README_FORMAT("\"Illegal arguments or inputs. Please refer to readme for the input data format.\""),
	SPLIT_REGEX("\\s+"),
	INPUT_FILE_DOESNT_EXIST("Input file doesn't exist"), COLON_STRING(":"),
	INPUT_FILE_LOC_MISSING_MSG("input file location need to be passed."),
	MAP_DOESNT_CONTAIN_STARTING_VERTEXT_NAMED("This DijkstraGraphMap does not contain the starting Vertex named:"),
	GRAPH_DOESNT_CONTAIN_END_VERTEX("Graph doesn't contain end vertex : ")
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
