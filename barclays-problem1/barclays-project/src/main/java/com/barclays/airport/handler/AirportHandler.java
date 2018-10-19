package com.barclays.airport.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.barclays.airport.routing.DijkstraAlgorithm;
import com.barclays.airport.routing.DijkstraAlgorithmFactory;
import com.barclays.airport.routing.model.Bag;
import com.barclays.airport.routing.model.DirectedEdge;
import com.barclays.airport.util.AirportConstants;

public class AirportHandler {

	public AirportHandler() {
	}

	public void process(String inputFileParameterString) {
		Scanner scan = getScanner(inputFileParameterString);// too much code in
															// main class
		if (scan != null) {
			List<DirectedEdge> edges = new ArrayList<DirectedEdge>();
			Map<String, String> departuresMap = new HashMap<String, String>();
			List<Bag> bagList = new ArrayList<Bag>();

			populateGraph(scan, edges, departuresMap, bagList);
			// need to add processData class
			if (!edges.isEmpty() && !departuresMap.isEmpty() && !bagList.isEmpty()) {				
				DijkstraAlgorithm dijkstraAlgorithm = DijkstraAlgorithmFactory.createDijkstraAlgorithm();// FactoryDP
				for (Bag bag : bagList) {
					String bagId = bag.getId();
					String entryGate = bag.getEntryGate();
					String flight = bag.getFlight();

					String destGate;
					if (flight.equals(AirportConstants.FLIGHT_ARRIVAL.toString())) {
						destGate = AirportConstants.DEST_BAGGAGE_CLAIM.toString();
					} else {
						destGate = departuresMap.get(flight);
					}
					String pathLine = dijkstraAlgorithm.findShortestPath(entryGate, destGate, edges);
					System.out.println(bagId + AirportConstants.SINGLE_WHITE_SPACE.toString() + pathLine);
				}
			}
			// else{
			// //problem in initializing graph : ex
			// }
		}
	}

	private void populateGraph(Scanner scan, List<DirectedEdge> edges, Map<String, String> departuresMap,
			List<Bag> bagList) {
		parseInputGraph(scan, edges);
		parseInputDepartures(scan, departuresMap);
		parseInputBags(scan, bagList);
	}

	private Scanner getScanner(String inputFileString) {
		Scanner scan = null; // modularize it-have another class to take the
								// input
		File inputDataFile = new File(inputFileString.trim());
		if (inputDataFile.exists()) {
			try {
				scan = new Scanner(inputDataFile);
			} catch (FileNotFoundException fnfex) {
				System.out.println("Input file doesn't exist");				
			}
		} else {
			System.out.println("Input file doesn't exist");			
		}
		return scan;
	}

	/**
	 * Parse input graph and return back list.
	 * 
	 * @param scanner
	 * @param edges2 
	 * @return
	 */
	private void parseInputGraph(final Scanner scanner, List<DirectedEdge> edges2) {
		if (scanner == null) {
			throw new IllegalArgumentException(
					"Illegal arguments or inputs. Please refer to readme for the input data format.");

		}
		
		if (scanner.hasNextLine()) {
			String graphSection = scanner.nextLine();
			if (!graphSection.startsWith(AirportConstants.INPUT_DATA_SECTION_HEAD.toString())) {
				throw new IllegalArgumentException(
						"Illegal arguments or inputs. Please refer to readme for the input data format.");
			}
			if (scanner.hasNextLine()) {
				String next = scanner.nextLine();
				
				while (!next.startsWith(AirportConstants.INPUT_DATA_SECTION_HEAD.toString())) {
					String[] parts = next.trim().split("\\s+");// https://stackoverflow.com/questions/225337/how-do-i-split-a-string-with-any-whitespace-chars-as-delimiters
					if (parts.length >= 3) {
						DirectedEdge directedEdge = new DirectedEdge(parts[0], parts[1], Integer.valueOf(parts[2]));
						edges2.add(directedEdge);
						// Since it is bi-direction edge, will add another
						// direction
						// edge too.
						DirectedEdge rDirectedEdge = new DirectedEdge(parts[1], parts[0], Integer.valueOf(parts[2]));
						edges2.add(rDirectedEdge);
					} else {
						throw new IllegalArgumentException(
								"Illegal arguments or inputs. Please refer to readme for the input data format.");
					}
					if (scanner.hasNextLine())
						next = scanner.nextLine();
					else
						break;
				}
			}
		}
	}

	/**
	 * Parse input departures and return back map.
	 * 
	 * @param scanner
	 * @param departuresMap2 
	 * @return
	 */
	private void parseInputDepartures(final Scanner scanner, Map<String, String> departuresMap2) {
		if (scanner == null) {
			throw new IllegalArgumentException(
					"Illegal arguments or inputs. Please refer to readme for the input data format.");
		}
		if (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			
			while (!next.startsWith(AirportConstants.INPUT_DATA_SECTION_HEAD.toString())) {
				String[] parts = next.trim().split("\\s+");
				if (parts.length >= 2) {
					departuresMap2.put(parts[0], parts[1]);
				} else {
					throw new IllegalArgumentException(
							"Illegal arguments or inputs. Please refer to readme for the input data format.");
				}
				if (scanner.hasNextLine())
					next = scanner.nextLine();
				else
					break;
			}
		}		
	}

	/**
	 * This will parse input bags and return back the list.
	 * 
	 * @param scanner
	 * @param bagList2 
	 * @return
	 */
	private void parseInputBags(final Scanner scanner, List<Bag> bagList2) {
		if (scanner == null) {
			throw new IllegalArgumentException(
					"Illegal arguments or inputs. Please refer to readme for the input data format.");
		}
		String next;		
		while (scanner.hasNextLine()) {
			next = scanner.nextLine();
			String[] parts = next.trim().split("\\s+");
			if (parts.length >= 3) {
				Bag bag = new Bag(parts[0], parts[1], parts[2]);
				bagList2.add(bag);
			} else {
				scanner.close();
				break;
			}
		}
	}

}
