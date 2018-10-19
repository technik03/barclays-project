package com.barclays.airport;

import com.barclays.airport.handler.AirportHandler;

/**
 * Main driver program
 *
 */
public class AirportApp {  

  public static void main(String[] args) { //have 3 sections, and add comments to those like read data get data, apply algo, output format op- everything else goes into the classes
    if (args.length != 1) {
      System.out.println("input file location need to be passed.");
      System.exit(1);
    }
    
    AirportHandler airportHandler = new AirportHandler();
    airportHandler.process(args[0]);
    
  }


}
