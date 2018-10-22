package com.barclays.theater.seating;

import java.util.List;
import java.util.TreeMap;
import com.barclays.theater.model.Customer;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.util.TheaterConstants;

/**
 * This represent seating and customer order for customer.
 * 
 * @author niharika
 *
 */
public class SeatingAndCustomerOrder {
  private Integer highestSeatSection;
  private Integer totalTheaterSeats;

  public SeatingAndCustomerOrder() {
    super();
  }

  /**
   * This is used to extract seating and customer order.
   * 
   * @param seatingAndCustomerRequest
   */
  public void extractSeatingAndCustomerOrder(final CustomerSeatingRequest seatingAndCustomerRequest) {
    if (seatingAndCustomerRequest == null) {
      return;
    }
    for (int i = 0, j = 1, partyKey = 1; i < seatingAndCustomerRequest.getTotalLines(); i++, j++) {
      if (i > seatingAndCustomerRequest.getEmptyLineNumber()) {
        partyKey =
            extractCustomerOrder(seatingAndCustomerRequest.getCustomerMap(),
                seatingAndCustomerRequest.getLineReaderText(), i, partyKey);
      } else if (i < seatingAndCustomerRequest.getEmptyLineNumber()) {
        extractSeatingMatrix(seatingAndCustomerRequest.getLineReaderText(),
            seatingAndCustomerRequest.getSeatingMatrix(), i, j);
      }
    }
  }

  /**
   * Generate seating matrix.
   * 
   * @param lineReaderText
   * @param seatingMatrix
   * @param i
   * @param j
   */
  private void extractSeatingMatrix(final List<StringBuilder> lineReaderText,
      final Integer[][] seatingMatrix, final Integer i, final Integer j) {
    if (!lineReaderText.isEmpty()) {
      String[] sectionList = lineReaderText.get(i).toString().split(TheaterConstants.SINGLE_SPACE.toString());
      for (int section = 0, k = 1; section < sectionList.length; section++, k++) {
        int sectionSeatsCount = Integer.parseInt(sectionList[section]);
        highestSeatSection = Math.max(highestSeatSection, sectionSeatsCount);
        totalTheaterSeats = totalTheaterSeats + sectionSeatsCount;
        seatingMatrix[j][k] = sectionSeatsCount;
      }
    }
  }

  /**
   * This is used to extract customer order by using the party key
   * 
   * @param customerMap
   * @param lineReaderText
   * @param i
   * @param partyKey
   * @return
   */
  private int extractCustomerOrder(final TreeMap<Integer, Customer> customerMap,
      final List<StringBuilder> lineReaderText, final Integer i, Integer partyKey) {
    if (!lineReaderText.isEmpty()) {
      String[] custmomerList = lineReaderText.get(i).toString().split(" ");
      Customer seatingSection = new Customer(custmomerList[0], Integer.parseInt(custmomerList[1]));
      customerMap.put(partyKey++, seatingSection);
    }
    return partyKey;
  }

  public Integer getHighestSeatSection() {
    return highestSeatSection;
  }

  public Integer getTotalTheaterSeats() {
    return totalTheaterSeats;
  }

  @Override
  public String toString() {
    return "SeatingAndCustomerOrder [highestSeatSection=" + highestSeatSection
        + ", totalTheaterSeats=" + totalTheaterSeats + "]";
  }
}
