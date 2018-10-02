package com.barclays.airport.routing.model;

public class DijkstraException extends RuntimeException {
  private static final long serialVersionUID = -8543680804716947114L;

  public DijkstraException(String message) {
    super(message);
  }
}
