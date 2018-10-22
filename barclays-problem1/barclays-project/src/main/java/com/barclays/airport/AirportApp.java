package com.barclays.airport;

import com.barclays.airport.handler.AirportHandler;
import com.barclays.airport.util.AirportConstants;

/**
 * Main driver program
 * 
 * @author niharika
 *
 */
public class AirportApp {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(AirportConstants.INPUT_FILE_LOC_MISSING_MSG.toString());
			System.exit(1);
		}

		AirportHandler airportHandler = new AirportHandler();
		airportHandler.process(args[0]);

	}

}
