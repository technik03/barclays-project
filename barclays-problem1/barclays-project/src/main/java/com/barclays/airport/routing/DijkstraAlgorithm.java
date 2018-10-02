package com.barclays.airport.routing;

import java.util.List;

import com.barclays.airport.routing.model.DirectedEdge;

public interface DijkstraAlgorithm {
  public String findShortestPath(String entry, String dest, List<DirectedEdge> graphEdges);
}
