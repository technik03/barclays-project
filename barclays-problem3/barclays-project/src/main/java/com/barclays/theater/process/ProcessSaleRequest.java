package com.barclays.theater.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import com.barclays.theater.app.TheaterApp;
import com.barclays.theater.model.Customer;
import com.barclays.theater.model.CustomerSeatingRequest;

/**
 * This class represents sale request object.
 *
 */
public class ProcessSaleRequest {

  /**
   * It tries to load and parses the input file. And adds the extracted info into the
   * CustomerSeatingRequest object.
   *
   * @param fileName
   * @return
   */
  public CustomerSeatingRequest extractInfoFromFile(final String fileName) {
    InputStream resourceAsStream = null;
    CustomerSeatingRequest CustomerSeatingRequest = null;
    TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();

    try {
      LineNumberReader in =
          new LineNumberReader(new InputStreamReader(TheaterApp.class.getClassLoader()
              .getResourceAsStream(fileName)));
      int emptyLineNumber = 0;
      Integer totalLines = new Integer(0);

      List<StringBuilder> lineReaderText = new ArrayList<>();
      String line = null;

      while ((line = in.readLine()) != null) {
        lineReaderText.add(totalLines, new StringBuilder(line));
        if (line.trim().isEmpty()) {
          emptyLineNumber = in.getLineNumber() - 1;
        }
        totalLines++;
      }

      CustomerSeatingRequest =
          setCustomerSeatingRequest(customerMap, emptyLineNumber, totalLines, lineReaderText);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.out.println("Sorry, error while reading input file");
    } finally {
      if (resourceAsStream != null) {
        try {
          resourceAsStream.close();
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println("Sorry, error while closing input reader");
        }
      }
    }
    return CustomerSeatingRequest;
  }

  private CustomerSeatingRequest setCustomerSeatingRequest(
      final TreeMap<Integer, Customer> customerMap, final int emptyLineNumber,
      final Integer totalLines, final List<StringBuilder> lineReaderText) {
    CustomerSeatingRequest CustomerSeatingRequest;
    int[][] seatingMatrix = new int[6][6];
    CustomerSeatingRequest = new CustomerSeatingRequest();
    CustomerSeatingRequest.setEmptyLineNumber(emptyLineNumber);
    CustomerSeatingRequest.setLineReaderText(lineReaderText);
    CustomerSeatingRequest.setCustomerMap(customerMap);
    CustomerSeatingRequest.setSeatingMatrix(seatingMatrix);
    CustomerSeatingRequest.setTotalLines(totalLines);
    return CustomerSeatingRequest;
  }

}
