package com.barclays.theater.test;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.process.ProcessSaleRequest;
import com.barclays.theater.seating.CustomerSeatingAssignment;
import com.barclays.theater.seating.SeatingAndCustomerOrder;

public class CustomerSeatingAssignmentTest {

  @Test
  public void test() {

    ProcessSaleRequest psr = new ProcessSaleRequest();
    CustomerSeatingRequest csr = psr.extractInfoFromFile("theater.txt");

    SeatingAndCustomerOrder extractSeatingAndCustomerOrder = new SeatingAndCustomerOrder();
    extractSeatingAndCustomerOrder.extractSeatingAndCustomerOrder(csr);

    CustomerSeatingAssignment csa = new CustomerSeatingAssignment();
    csa.processSeating(csr, extractSeatingAndCustomerOrder.getHighestSeatSection(),
        extractSeatingAndCustomerOrder.getTotalTheaterSeats());
    assertNotNull(csa);

  }

}
