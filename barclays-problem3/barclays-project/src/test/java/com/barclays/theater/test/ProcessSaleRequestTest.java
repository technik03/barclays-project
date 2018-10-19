package com.barclays.theater.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.process.ProcessSaleRequest;

public class ProcessSaleRequestTest {

  @Test
  public void test() {
    ProcessSaleRequest psr = new ProcessSaleRequest();
    CustomerSeatingRequest csr = psr.extractInfoFromFile("theater.txt");
    assertNotNull(csr);
  }

}
