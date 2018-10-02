package com.barclays.airport.routing;

public class DijkstraAlgorithmFactory {

  public static DijkstraAlgorithm createDijkstraAlgorithm() {
    return new DijkstraAlgorithmImpl();
  }
}
