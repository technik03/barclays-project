package com.barclays.airport.driver;

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

/**
 * Main driver program
 *
 */
public class AirportApp {
  private static final String INPUT_DATA_SECTION_HEAD = "# Section:";
  private static final String FLIGHT_ARRIVAL = "ARRIVAL";
  private static final String DEST_BAGGAGE_CLAIM = "BaggageClaim";
  private static final String SINGLE_WHITE_SPACE = " ";

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("input file location need to be passed.");
      System.exit(1);
    }
    Scanner scan = null;
    File inputDataFile = new File(args[0].trim());
    if (inputDataFile.exists()) {
      try {
        scan = new Scanner(inputDataFile);
      } catch (FileNotFoundException fnfex) {
        System.out.println("Input file doesn't exist");
        System.exit(1);
      }
    } else {
      System.out.println("Input file doesn't exist");
      System.exit(1);
    }
    if (scan != null) {
      List<DirectedEdge> edges = parseInputGraph(scan);
      DijkstraAlgorithm dijkstraAlgorithm = DijkstraAlgorithmFactory.createDijkstraAlgorithm();
      Map<String, String> departuresMap = parseInputDepartures(scan);
      List<Bag> bagList = parseInputBags(scan);
      scan.close();
      for (Bag bag : bagList) {
        String bagId = bag.getId();
        String entryGate = bag.getEntryGate();
        String flight = bag.getFlight();

        String destGate;
        if (flight.equals(FLIGHT_ARRIVAL)) {
          destGate = DEST_BAGGAGE_CLAIM;
        } else {
          destGate = departuresMap.get(flight);
        }
        String pathLine = dijkstraAlgorithm.findShortestPath(entryGate, destGate, edges);
        System.out.println(bagId + SINGLE_WHITE_SPACE + pathLine);
      }
    }
  }

  /**
   * Parse input graph and return back list.
   * 
   * @param scanner
   * @return
   */
  private static List<DirectedEdge> parseInputGraph(final Scanner scanner) {
    String graphSection = scanner.nextLine();
    if (!graphSection.startsWith(INPUT_DATA_SECTION_HEAD)) {
      throw new IllegalArgumentException(
          "Illegal arguments or inputs. Please refer to readme for the input data format.");
    }
    String next = scanner.nextLine();
    List<DirectedEdge> edges = new ArrayList<>();
    while (!next.startsWith(INPUT_DATA_SECTION_HEAD)) {
      String[] parts = next.trim().split("\\s+");
      if (parts.length >= 3) {
        DirectedEdge directedEdge = new DirectedEdge(parts[0], parts[1], Integer.valueOf(parts[2]));
        edges.add(directedEdge);
        // Since it is bi-direction edge, will add another direction edge too.
        DirectedEdge rDirectedEdge =
            new DirectedEdge(parts[1], parts[0], Integer.valueOf(parts[2]));
        edges.add(rDirectedEdge);
      } else {
        throw new IllegalArgumentException(
            "Illegal arguments or inputs. Please refer to readme for the input data format.");
      }
      next = scanner.nextLine();
    }
    return edges;
  }

  /**
   * Parse input departures and return back map.
   * 
   * @param scanner
   * @return
   */
  private static Map<String, String> parseInputDepartures(final Scanner scanner) {
    String next = scanner.nextLine();
    Map<String, String> departuresMap = new HashMap<>();
    while (!next.startsWith(INPUT_DATA_SECTION_HEAD)) {
      String[] parts = next.trim().split("\\s+");
      if (parts.length >= 2) {
        departuresMap.put(parts[0], parts[1]);
      } else {
        throw new IllegalArgumentException(
            "Illegal arguments or inputs. Please refer to readme for the input data format.");
      }
      next = scanner.nextLine();
    }
    return departuresMap;
  }

  /**
   * This will parse input bags and return back the list.
   * 
   * @param scanner
   * @return
   */
  private static List<Bag> parseInputBags(final Scanner scanner) {
    String next;
    List<Bag> bagList = new ArrayList<>();
    do {
      next = scanner.nextLine();
      String[] parts = next.trim().split("\\s+");
      if (parts.length >= 3) {
        Bag bag = new Bag(parts[0], parts[1], parts[2]);
        bagList.add(bag);
      } else {
        scanner.close();
        break;
      }
    } while (scanner.hasNextLine());
    return bagList;
  }
}
