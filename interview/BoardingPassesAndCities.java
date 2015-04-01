/*
 Given a collection of boarding passes, the starting city and the destination city, decide if one
 can get to the destination from the start city.

   graph:
    MIA -> DC
    DC -> NYC
    DC-> MIA

    def can_reach(graph, MIA, NYC)
 */

import java.util.*;

public class BoardingPassesAndCities {
  class City {
    String name;
    City[] reachables;
  }

  boolean canReach(City c1, City c2) {
    Set<City> visited = new HashSet<City>();
    LinkedList<City> queue = LinkedList<City>();
    if (c1.name.equals(c2.name)) {
      return true;
    }
    visited.add(c1);
    queue.add(c1);
    while (!queue.isEmpty()) {
      City c = queue.poll();
      for (City city : c.reachables) {
        if (!visited.contains(city)) { // NOTE: CHECK VISITED FIRST
          if (city.name.equals(c2.name)) {
            return true;
          }
          queue.add(city);
          visited.add(city);
        }
      }
    }
    return false;
  }
}