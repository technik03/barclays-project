package com.barclays.theater.seating;

import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import com.barclays.theater.model.Customer;

/**
 * This will dump theater seating.
 * 
 */
public class DumpTheaterSeating {

  /**
   * Retrieves Customer Name and assignment info from the Map and Sysouts the final seating
   * information.
   *
   * @param customerTreeMap
   */
  public void printSeatingAssignments(final TreeMap<Integer, Customer> customerTreeMap) {
    customerTreeMap.forEach((key, customer) -> {
      StringBuilder printAssignment = new StringBuilder();
      printAssignment.append(customer.getName());
      if (StringUtils.isBlank(customer.getComments()) && customer.getRowAssigned() != null
          && customer.getSectionAssigned() != null) {
        printAssignment.append(" Row " + customer.getRowAssigned() + " Section "
            + customer.getSectionAssigned());

      } else if (StringUtils.isNotBlank(customer.getComments())) {
        printAssignment.append(customer.getComments());
      }
      System.out.println(printAssignment);
    });
  }
}
