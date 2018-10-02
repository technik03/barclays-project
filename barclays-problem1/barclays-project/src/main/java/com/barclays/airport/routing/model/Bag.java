package com.barclays.airport.routing.model;

public class Bag {
  private final String id;
  private final String entryGate;
  private final String flight;

  public Bag(String id, String entryGate, String flight) {
    this.id = id;
    this.entryGate = entryGate;
    this.flight = flight;
  }

  public String getId() {
    return id;
  }

  public String getEntryGate() {
    return entryGate;
  }

  public String getFlight() {
    return flight;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((entryGate == null) ? 0 : entryGate.hashCode());
    result = prime * result + ((flight == null) ? 0 : flight.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    Bag other = (Bag) obj;
    if (entryGate == null) {
      if (other.entryGate != null)
        return false;
    } else if (!entryGate.equals(other.entryGate))
      return false;
    if (flight == null) {
      if (other.flight != null)
        return false;
    } else if (!flight.equals(other.flight))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Bag [id=" + id + ", entryGate=" + entryGate + ", flight=" + flight + "]";
  }
}
