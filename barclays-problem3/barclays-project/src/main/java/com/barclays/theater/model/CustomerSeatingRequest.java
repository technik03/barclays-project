package com.barclays.theater.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * CustomerSeatingRequest is the object model of all the information about the seating in the
 * theater and Customer request fed into this Exercise in file format.
 */
public class CustomerSeatingRequest {
  private TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
  private List<StringBuilder> lineReaderText = new ArrayList<>();
  private int emptyLineNumber = 0;
  private Integer totalLines = new Integer(0);
  private int[][] seatingMatrix = new int[6][6];

  public TreeMap<Integer, Customer> getCustomerMap() {
    return customerMap;
  }

  public void setCustomerMap(TreeMap<Integer, Customer> customerMap) {
    this.customerMap = customerMap;
  }

  public int getEmptyLineNumber() {
    return emptyLineNumber;
  }

  public void setEmptyLineNumber(int emptyLineNumber) {
    this.emptyLineNumber = emptyLineNumber;
  }

  public Integer getTotalLines() {
    return totalLines;
  }

  public void setTotalLines(Integer totalLines) {
    this.totalLines = totalLines;
  }

  public int[][] getSeatingMatrix() {
    return seatingMatrix;
  }

  public void setSeatingMatrix(int[][] seatingMatrix) {
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
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CustomerSeatingRequest other = (CustomerSeatingRequest) obj;
    if (customerMap == null) {
      if (other.customerMap != null)
        return false;
    } else if (!customerMap.equals(other.customerMap))
      return false;
    if (emptyLineNumber != other.emptyLineNumber)
      return false;
    if (lineReaderText == null) {
      if (other.lineReaderText != null)
        return false;
    } else if (!lineReaderText.equals(other.lineReaderText))
      return false;
    if (!Arrays.deepEquals(seatingMatrix, other.seatingMatrix))
      return false;
    if (totalLines == null) {
      if (other.totalLines != null)
        return false;
    } else if (!totalLines.equals(other.totalLines))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CustomerSeatingRequest [customerMap=" + customerMap + ", lineReaderText="
        + lineReaderText + ", emptyLineNumber=" + emptyLineNumber + ", totalLines=" + totalLines
        + ", seatingMatrix=" + Arrays.toString(seatingMatrix) + "]";
  }
}
