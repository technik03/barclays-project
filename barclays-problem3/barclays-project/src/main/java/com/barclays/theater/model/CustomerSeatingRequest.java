package com.barclays.theater.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * CustomerSeatingRequest is the object model of all the information about the seating in the
 * theater and Customer request fed into this Exercise in file format.
 * 
 * @author niharika
 */
public class CustomerSeatingRequest {
  private TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
  private List<StringBuilder> lineReaderText = new ArrayList<StringBuilder>();
  private Integer emptyLineNumber = 0;
  private Integer totalLines = new Integer(0);
  private Integer[][] seatingMatrix = new Integer[6][6];

  public CustomerSeatingRequest() {
    super();
  }

  public CustomerSeatingRequest(TreeMap<Integer, Customer> customerMap,
      List<StringBuilder> lineReaderText, Integer emptyLineNumber, Integer totalLines,
      Integer[][] seatingMatrix) {
    super();
    this.customerMap = customerMap;
    this.lineReaderText = lineReaderText;
    this.emptyLineNumber = emptyLineNumber;
    this.totalLines = totalLines;
    this.seatingMatrix = seatingMatrix;
  }

  public TreeMap<Integer, Customer> getCustomerMap() {
    return customerMap;
  }

  public void setCustomerMap(TreeMap<Integer, Customer> customerMap) {
    this.customerMap = customerMap;
  }

  public Integer getEmptyLineNumber() {
    return emptyLineNumber;
  }

  public void setEmptyLineNumber(Integer emptyLineNumber) {
    this.emptyLineNumber = emptyLineNumber;
  }

  public Integer getTotalLines() {
    return totalLines;
  }

  public void setTotalLines(Integer totalLines) {
    this.totalLines = totalLines;
  }

  public Integer[][] getSeatingMatrix() {
    return seatingMatrix;
  }

  public void setSeatingMatrix(Integer[][] seatingMatrix) {
    this.seatingMatrix = seatingMatrix;
  }

  public List<StringBuilder> getLineReaderText() {
    return lineReaderText;
  }

  public void setLineReaderText(List<StringBuilder> lineReaderText) {
    this.lineReaderText = lineReaderText;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((customerMap == null) ? 0 : customerMap.hashCode());
    result = prime * result + emptyLineNumber;
    result = prime * result + ((lineReaderText == null) ? 0 : lineReaderText.hashCode());
    result = prime * result + Arrays.hashCode(seatingMatrix);
    result = prime * result + ((totalLines == null) ? 0 : totalLines.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
	  if (this == obj)
		  return true;
	  if (obj == null || getClass() != obj.getClass())
		  return false;

	  CustomerSeatingRequest other = (CustomerSeatingRequest) obj;
	  if ((customerMap == null && other.customerMap != null)
			  || (customerMap!=null && !customerMap.equals(other.customerMap))) 
		  return false;    
	  else if (emptyLineNumber != other.emptyLineNumber)
		  return false;
	  else if ((lineReaderText == null && other.lineReaderText != null)
			  || (lineReaderText!=null && !lineReaderText.equals(other.lineReaderText))) 
		  return false;
	  //Using DeepEquals to compare the values of each index of the arrays.
	  else if (!Arrays.deepEquals(seatingMatrix, other.seatingMatrix))
		  return false;

	  else if ((totalLines == null && other.totalLines != null)
			  || (totalLines!=null && !totalLines.equals(other.totalLines))) 
		  return false;
	  else
		  return true;
  }

  @Override
  public String toString() {
    return "CustomerSeatingRequest [customerMap=" + customerMap + ", lineReaderText="
        + lineReaderText + ", emptyLineNumber=" + emptyLineNumber + ", totalLines=" + totalLines
        + ", seatingMatrix=" + Arrays.toString(seatingMatrix) + "]";
  }
}
