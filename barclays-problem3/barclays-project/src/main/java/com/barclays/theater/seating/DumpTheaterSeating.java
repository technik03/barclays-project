package com.barclays.theater.seating;

import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import com.barclays.theater.model.Customer;
import com.barclays.theater.util.TheaterConstants;

/**
 * This will dump theater seating using customer tree.
 * 
 * @author niharika
 * 
 */
public class DumpTheaterSeating {

  public DumpTheaterSeating() {
    super();
  }

  /**
   * Retrieves Customer Name and assignment info from the Map and Sysouts the final seating
   * information.
   *
   * @param customerTreeMap
   */
  public void printSeatingAssignments(final TreeMap<Integer, Customer> customerTreeMap) {
    // using java 8 feature
    customerTreeMap.forEach((key, customer) -> {
      StringBuilder printAssignment = new StringBuilder();
      printAssignment.append(customer.getName());
      if (StringUtils.isBlank(customer.getComments()) && customer.getRowAssigned() != null
          && customer.getSectionAssigned() != null) {
        printAssignment.append(TheaterConstants.ROW.toString() + customer.getRowAssigned() + TheaterConstants.SECTION.toString()
            + customer.getSectionAssigned());

      } else if (StringUtils.isNotBlank(customer.getComments())) {
        printAssignment.append(customer.getComments());
      }
      System.out.println(printAssignment);
    });
  }
}
