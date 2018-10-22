package com.barclays.airport.routing;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.barclays.airport.routing.model.DijkstraGraphMap;
import com.barclays.airport.routing.model.DirectedEdge;
import com.barclays.airport.routing.model.Vertex;
import com.barclays.airport.util.AirportConstants;

/**
 * Implementation class for DijkstraAlgorithm
 *
 */
public class DijkstraAlgorithmImpl implements DijkstraAlgorithm {
	private final Map<String, DijkstraGraphMap> visitedMap = new ConcurrentHashMap<>();

	/**
	 * Used to find the shortest path given the start and end points.
	 */
	@Override
	public String findShortestPath(final String entryGate, final String destGate, final List<DirectedEdge> edges) {
		DijkstraGraphMap dijkstraGraphMap;
		//If entry gate has been visited, get its node map; else create it 
		if (visitedMap.containsKey(entryGate)) {
			dijkstraGraphMap = visitedMap.get(entryGate);
		} else {
			dijkstraGraphMap = new DijkstraGraphMap(edges);
			dijkstraGraphMap.dijkstra(entryGate);
			visitedMap.put(entryGate, dijkstraGraphMap);
		}
		List<Vertex> shortestPath = dijkstraGraphMap.getShortestPath(destGate);
		return generatePathLine(shortestPath);
	}

	/**
	 * Generate path line given the vertex path
	 * 
	 * @param path
	 * @return
	 */
	private String generatePathLine(final List<Vertex> path) {
		StringBuilder line = new StringBuilder();
		for (Vertex vertex : path) {
			line.append(vertex.getName()).append(AirportConstants.SINGLE_WHITE_SPACE.toString());
		}
		line.append(AirportConstants.COLON_STRING+AirportConstants.SINGLE_WHITE_SPACE.toString()).append(path.get(path.size() - 1).getTime());
		return line.toString();
	}

}
