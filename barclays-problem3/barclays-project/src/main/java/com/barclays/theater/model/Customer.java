package com.barclays.theater.model;

import java.io.Serializable;

/**
 * Model object that represents a theater for Customer.
 */
public class Customer implements Serializable {
  private static final long serialVersionUID = 2192027696636179257L;
  public String name;
  public Integer rowAssigned;
  public Integer sectionAssigned;
  public String comments;
  public int seatingNeeded;

  public Customer(String name, int seatingNeeded) {
    this.name = name;
    this.seatingNeeded = seatingNeeded;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRowAssigned() {
    return rowAssigned;
  }

  public void setRowAssigned(Integer rowAssigned) {
    this.rowAssigned = rowAssigned;
  }

  public Integer getSectionAssigned() {
    return sectionAssigned;
  }

  public void setSectionAssigned(Integer sectionAssigned) {
    this.sectionAssigned = sectionAssigned;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public int getSeatingNeeded() {
    return seatingNeeded;
  }

  public void setSeatingNeeded(int seatingNeeded) {
    this.seatingNeeded = seatingNeeded;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((comments == null) ? 0 : comments.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((rowAssigned == null) ? 0 : rowAssigned.hashCode());
    result = prime * result + seatingNeeded;
    result = prime * result + ((sectionAssigned == null) ? 0 : sectionAssigned.hashCode());
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
    Customer other = (Customer) obj;
    if (comments == null) {
      if (other.comments != null)
        return false;
    } else if (!comments.equals(other.comments))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (rowAssigned == null) {
      if (other.rowAssigned != null)
        return false;
    } else if (!rowAssigned.equals(other.rowAssigned))
      return false;
    if (seatingNeeded != other.seatingNeeded)
      return false;
    if (sectionAssigned == null) {
      if (other.sectionAssigned != null)
        return false;
    } else if (!sectionAssigned.equals(other.sectionAssigned))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Customer [name=" + name + ", rowAssigned=" + rowAssigned + ", sectionAssigned="
        + sectionAssigned + ", comments=" + comments + ", seatingNeeded=" + seatingNeeded + "]";
  }
}
