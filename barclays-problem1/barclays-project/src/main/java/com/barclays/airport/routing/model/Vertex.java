/**
 * @author niharika
 */
package com.barclays.airport.routing.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barclays.airport.util.AirportConstants;

public class Vertex implements Comparable<Vertex> {
	private final String name;
	// Will be the total time for the shortest path to this Vertex from the
	// source Vertex specfied in
	// the DijkstraGraphMap
	private int time;
	private Vertex prevVertext = null;
	private Map<Vertex, Integer> neighbours;

	// constructor
	public Vertex(String name) {
		this.name = name;
		neighbours = new HashMap<>();
		time = Integer.MAX_VALUE;
	}

	public String getName() {
		return this.name;
	}

	public void setPrevVertext(Vertex prevVertext) {
		this.prevVertext = prevVertext;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Vertex getPrevVertext() {
		return prevVertext;
	}

	public Map<Vertex, Integer> getNeighbours() {
		return neighbours;
	}

	public int compareTo(Vertex other) {
		if (time == other.time)
			return name.compareTo(other.name);

		return Integer.compare(time, other.time);
	}

	/**
	 * Return the shortest path to this Vertex from a source specified in a
	 * DijkstraGraphMap after running the dijkstra algorithm
	 * 
	 * @return The list of the shortest vertex path
	 */

	public List<Vertex> getShortestPathTo() {
		final List<Vertex> path = new ArrayList<Vertex>();
		path.add(this);
		Vertex vertex = this.getPrevVertext();
		while (vertex != null && !path.contains(vertex)) {
			path.add(vertex);
			vertex = vertex.getPrevVertext();
		}
		Collections.reverse(path);
		return path;
	}

	@Override
	public String toString() {
		return this.name + AirportConstants.COLON_STRING.toString() + this.time;
	}
}
