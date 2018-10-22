package com.barclays.theater.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.barclays.theater.TheaterApp;
import com.barclays.theater.model.Customer;
import com.barclays.theater.model.CustomerSeatingRequest;
import com.barclays.theater.util.TheaterConstants;

/**
 * This class represents sale request object. It extracts info from the file.
 * 
 * @author niharika
 *
 */
public class ProcessSaleRequest {

  public ProcessSaleRequest() {
    super();
  }

  /**
   * It tries to load and parses the input file. And adds the extracted info into the
   * CustomerSeatingRequest object.
   *
   * @param fileName
   * @return
   */
  public CustomerSeatingRequest extractInfoFromFile(final String fileName) {
    if (fileName == null || fileName.isEmpty()) {
      return null;
    }
    InputStream resourceAsStream = null;
    CustomerSeatingRequest CustomerSeatingRequest = null;
    TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();

    try {
      LineNumberReader in =
          new LineNumberReader(new InputStreamReader(TheaterApp.class.getClassLoader()
              .getResourceAsStream(fileName)));
      Integer emptyLineNumber = 0;
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
      System.out.println(TheaterConstants.ERROR_WHILE_READING_INPUT_FILE.toString());
    } finally {
      if (resourceAsStream != null) {
        try {
          resourceAsStream.close();
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println(TheaterConstants.ERROR_WHILE_CLOSING_INPUT_READER.toString());
        }
      }
    }
    return CustomerSeatingRequest;
  }

  /**
   * This is used to set customer seating request.
   * 
   * @param customerMap
   * @param emptyLineNumber
   * @param totalLines
   * @param lineReaderText
   * @return
   */
  private CustomerSeatingRequest setCustomerSeatingRequest(
      final TreeMap<Integer, Customer> customerMap, final Integer emptyLineNumber,
      final Integer totalLines, final List<StringBuilder> lineReaderText) {
    CustomerSeatingRequest customerSeatingRequest;
    Integer[][] seatingMatrix = new Integer[6][6];
    customerSeatingRequest = new CustomerSeatingRequest();
    customerSeatingRequest.setEmptyLineNumber(emptyLineNumber);
    customerSeatingRequest.setLineReaderText(lineReaderText);
    customerSeatingRequest.setCustomerMap(customerMap);
    customerSeatingRequest.setSeatingMatrix(seatingMatrix);
    customerSeatingRequest.setTotalLines(totalLines);
    return customerSeatingRequest;
  }

}
