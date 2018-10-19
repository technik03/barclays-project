package com.barclays.theater.test;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.process.ProcessSaleRequest;
import com.barclays.theater.seating.SeatingAndCustomerOrder;

public class SeatingAndCustomerOrderTest {

  @Test
  public void test() {
    ProcessSaleRequest psr = new ProcessSaleRequest();
    CustomerSeatingRequest csr = psr.extractInfoFromFile("theater.txt");

    SeatingAndCustomerOrder extractSeatingAndCustomerOrder = new SeatingAndCustomerOrder();
    extractSeatingAndCustomerOrder.extractSeatingAndCustomerOrder(csr);
    assertNotNull(extractSeatingAndCustomerOrder);
  }

}
