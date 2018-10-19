package com.barclays.theater.seating;

import java.util.TreeMap;
import com.barclays.theater.model.Customer;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.resources.TheaterConstants;

/**
 * This class represents seating assignment for a customer.
 *
 * @author niharika
 *
 */
public class CustomerSeatingAssignment {
  private Integer highestSeatSection;
  private Integer totalTheaterSeats;

  public CustomerSeatingAssignment() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * This will assign seating to customers and handle split orders.
   *
   * @param seatingAndCustomerRequest
   * @param highestSeatSection
   * @param totalTheaterSeats
   */
  public void processSeating(final CustomerSeatingRequest seatingAndCustomerRequest,
      final Integer highestSeatSection, final Integer totalTheaterSeats) {
    this.highestSeatSection = highestSeatSection;
    this.totalTheaterSeats = totalTheaterSeats;
    assignSeatingToCustomers(seatingAndCustomerRequest.getCustomerMap(),
        seatingAndCustomerRequest.getSeatingMatrix());
    handleSplitOrders(seatingAndCustomerRequest.getCustomerMap());
    handleLargeOrders(seatingAndCustomerRequest.getCustomerMap());
  }


  /**
   * Handles any orders that are more than the number of seats in a given row by adding a message to
   * split the party.
   *
   * @param customerMap
   */
  private void handleSplitOrders(final TreeMap<Integer, Customer> customerMap) {
    // using java 8 feature
    customerMap.forEach((key, customer) -> {
      if (customer.getSeatingNeeded() > highestSeatSection) {
        customer.setComments(TheaterConstants.SPLIT_PARTY_MSG);
      }

    });
  }

  /**
   * Comapares number of seats requested with total number of seats in theater to number of seats
   * requested by a Customer. If Customer requests more than available appropriate message is added.
   * 
   * @param customerMap
   */
  private void handleLargeOrders(final TreeMap<Integer, Customer> customerMap) {
    // using java 8 feature
    customerMap.forEach((key, customer) -> {
      if (customer.getSeatingNeeded() > totalTheaterSeats) {
        customer.setComments(TheaterConstants.CANNOT_HANDLE_PARTY_MSG);
      }
    });
  }

  /**
   * Assigns seating to customer based on thier request.
   *
   * @param customerMap
   * @param seatingMatrix
   */
  private void assignSeatingToCustomers(final TreeMap<Integer, Customer> customerMap,
      final Integer[][] seatingMatrix) {
    // using java 8 feature
    customerMap.forEach((key, customer) -> {
      customerLoop: for (int row = 1; row < TheaterConstants.TOTAL_ROW; row++) {
        for (int section = 1; section < TheaterConstants.TOTAL_ROW; section++) {
          // If exact match and within 3 row
        if ((seatingMatrix[row][section] == customer.getSeatingNeeded() && row < 4)
            && customer.getRowAssigned() == null) {
          customer.setRowAssigned(row);
          customer.setSectionAssigned(section);
          break customerLoop;
        }
        if (seatingMatrix[row][section] == customer.getSeatingNeeded() && row >= 4
            && customer.getRowAssigned() == null) {
          // Check if higher number is available in front rows
          for (int rowRepeater = 1; rowRepeater < 4; rowRepeater++) {
            for (int sectionRepeater = 1; sectionRepeater < TheaterConstants.TOTAL_ROW; sectionRepeater++) {
              if ((seatingMatrix[rowRepeater][sectionRepeater] > customer.getSeatingNeeded())
                  && customer.getRowAssigned() == null) {
                customer.setRowAssigned(rowRepeater);
                customer.setSectionAssigned(sectionRepeater);
                seatingMatrix[rowRepeater][sectionRepeater] =
                    seatingMatrix[rowRepeater][sectionRepeater] - customer.getSeatingNeeded();
                break customerLoop;
              }
            }
            customer.setRowAssigned(row);
            customer.setSectionAssigned(section);
            break customerLoop;
          }
        }
      }
    }
  });
  }


}
