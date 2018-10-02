package com.barclays.theater.app;

import java.io.IOException;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.process.ProcessSaleRequest;
import com.barclays.theater.seating.CustomerSeatingAssignment;
import com.barclays.theater.seating.DumpTheaterSeating;
import com.barclays.theater.seating.SeatingAndCustomerOrder;

/**
 * Main app class to execute to see the desired output.
 */
public class TheaterApp {

  public static void main(String[] args) throws IOException {
    ProcessSaleRequest processPreSaleRequests = new ProcessSaleRequest();
    CustomerSeatingRequest seatingAndCustomerRequest =
        processPreSaleRequests.extractInfoFromFile("theater.txt");

    SeatingAndCustomerOrder extractSeatingAndCustomerOrder = new SeatingAndCustomerOrder();
    extractSeatingAndCustomerOrder.extractSeatingAndCustomerOrder(seatingAndCustomerRequest);

    new CustomerSeatingAssignment().processSeating(seatingAndCustomerRequest,
        extractSeatingAndCustomerOrder.getHighestSeatSection(),
        extractSeatingAndCustomerOrder.getTotalTheaterSeats());

    new DumpTheaterSeating().printSeatingAssignments(seatingAndCustomerRequest.getCustomerMap());
  }
}
