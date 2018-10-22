/**
 * @author niharika
 */
package com.barclays.airport.routing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.barclays.airport.util.AirportConstants;

public class DijkstraGraphMap {
	// mapping of vertex names to Vertex objects, built from a set of Edges
	private final Map<String, Vertex> graphMap;// ? =null

	// for each directed edge, it will add source and destination vertex.
	// for each edge, it will add neighbors to each source vertex
	public DijkstraGraphMap(final List<DirectedEdge> directedEdges) {
		graphMap = new HashMap<String, Vertex>(directedEdges.size());
		// Populate all the vertices from the edges
		for (DirectedEdge e : directedEdges) {// For each directed edge,
												// populate source and
												// destination vertex
			if (!graphMap.containsKey(e.getSource().getName()))
				graphMap.put(e.getSource().getName(), new Vertex(e.getSource().getName()));
			if (!graphMap.containsKey(e.getDestination().getName()))
				graphMap.put(e.getDestination().getName(), new Vertex(e.getDestination().getName()));
		}
		// Set all the neighbours
		for (DirectedEdge e : directedEdges) {
			graphMap.get(e.getSource().getName()).getNeighbours() 
					.put(graphMap.get(e.getDestination().getName()), e.getTime());// getNeighbours
			// will be empty so never do empty get
		}
	}

	/**
	 * Runs dijkstra algorithm using a specified source vertex. Every time when
	 * the starting vertex get changed, this algorithm should get run.
	 * 
	 * @param startName
	 *            the starting or source Vertex for the path
	 */
	public void dijkstra(final String startName) {
		// Sanity check
		if (!graphMap.containsKey(startName)) {
			throw new DijkstraException(
					AirportConstants.MAP_DOESNT_CONTAIN_STARTING_VERTEXT_NAMED.toString() + startName);
		}
		final Vertex source = graphMap.get(startName);
		NavigableSet<Vertex> queue = new TreeSet<>();

		// populate vertices to the queue
		for (Vertex v : graphMap.values()) {
			v.setPrevVertext(v == source ? source : null);
			v.setTime(v == source ? 0 : Integer.MAX_VALUE);
			queue.add(v);
		}

		dijkstra(queue);
	}

	/**
	 * Get the shortest path as a list of Vertex for a specific destination
	 * Vertex with name as endName
	 * 
	 * @param endName
	 *            the destination vertex name
	 * @return the shortest path as a List<Vertex>
	 */

	public List<Vertex> getShortestPath(final String endName) {
		//If graph does not contain node, then return exception
		if (!graphMap.containsKey(endName)) {
			throw new DijkstraException(AirportConstants.GRAPH_DOESNT_CONTAIN_END_VERTEX.toString() + endName);
		}

		return graphMap.get(endName).getShortestPathTo();
	}

	/**
	 * Using binary heap implement Dijkstra algo.
	 * 
	 * @param que
	 */
	private void dijkstra(final NavigableSet<Vertex> que) {
		Vertex source, neighbour;
		while (!que.isEmpty()) {

			source = que.pollFirst(); // vertex with shortest distance (first
										// iteration will return
										// source)
			if (source.getTime() == Integer.MAX_VALUE)
				break; // ignore u (and any other remaining vertices) since they
						// are unreachable

			// look at distances to each neighbour
			for (Map.Entry<Vertex, Integer> a : source.getNeighbours().entrySet()) {
				neighbour = a.getKey();

				final int alternateTime = source.getTime() + a.getValue();
				if (alternateTime < neighbour.getTime()) { // shorter path to
															// neighbour found
					que.remove(neighbour);
					neighbour.setTime(alternateTime);
					neighbour.setPrevVertext(source);
					que.add(neighbour);
				}
			}
		}
	}
}
