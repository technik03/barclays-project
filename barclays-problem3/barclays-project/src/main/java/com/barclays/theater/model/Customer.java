package com.barclays.theater.model;

import java.io.Serializable;

/**
 * Model object that represents a theater for Customer.
 * 
 * @author niharika
 */
public class Customer implements Serializable {
  private static final long serialVersionUID = 2192027696636179257L;
  public String name;
  public Integer rowAssigned;
  public Integer sectionAssigned;
  public String comments;
  public Integer seatingNeeded;

  public Customer() {
    super();
  }

  public Customer(String name, Integer seatingNeeded) {
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

  public Integer getSeatingNeeded() {
    return seatingNeeded;
  }

  public void setSeatingNeeded(Integer seatingNeeded) {
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

  //Override implementation - checking for obj being null or other private properties being null or different across this object's and obj's properties
  @Override
  public boolean equals(Object obj) {
	  if (this == obj)
		  return true;
	  //Checking for null && Comparing this and obj's objects
	  if (obj == null || getClass() != obj.getClass())
		  return false;    
	  Customer other = (Customer) obj;
	  //Return false if comments is null in one object and not in another or comments is not null and not equal in value.
	  if ((comments == null && other.comments != null)
			  || (comments != null && !comments.equals(other.comments)))    
		  return false;
	  //Return false if name is null in one object and not in another or name is not null and not equal in value.
	  else if ((name == null && other.name != null)
			  || (name != null && !name.equals(other.name)))
		  return false;
	  //Return false if rowAssigned is null in one object and not in another or rowAssigned is not null and not equal in value.
	  else if ((rowAssigned == null && other.rowAssigned != null)
			  || (rowAssigned!=null && !rowAssigned.equals(other.rowAssigned)))       
		  return false;
	  //Return false if the value for seatingNeeded does not match
	  else if (seatingNeeded != other.seatingNeeded)
		  return false;
	  //Return false if sectionAssigned is null in one object and not in another or sectionAssigned is not null and not equal in value.
	  else if ((sectionAssigned == null && other.sectionAssigned != null)
			  || (sectionAssigned!=null && !sectionAssigned.equals(other.sectionAssigned)))       
		  return false;
	  else
		  return true;
  }

  @Override
  public String toString() {
    return "Customer [name=" + name + ", rowAssigned=" + rowAssigned + ", sectionAssigned="
        + sectionAssigned + ", comments=" + comments + ", seatingNeeded=" + seatingNeeded + "]";
  }
}
