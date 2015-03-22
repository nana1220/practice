package ch10scalabilityandmemorylimits;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.HashMap;
/*
 * How would you design the data structures for a very large social network like Face-
 * book or Linked/n? Describe how you would design an algorithm to show the connection
 * or path, between two people (e.g., Me -> Bob -> Susan -> Jason -> You).
 */

/*
 * Single machine case: write a graph class, node represents person, ajacent are friends
 */
class Person1 {
  Person1[] friends;
}

/*
 * Large scale, hash table store Person ID, machine ID
 */
class Server {
  HashMap<Integer, Machine> machines; // or use UUID
  HashMap<Integer, Integer> persionToMachine;

  Machine getMachineById(Integer machineId) {
    return machines.get(machineId);
  }

  Integer getMachineForPerson(Integer personId) {
    return persionToMachine(persionId);
  }

  Person getPersonById(Integer personId) {
    Integer machineId = getMachineForPerson(personId);
    Machine machine = getMachineById(machineId);
    machine.getPersonById(personId);
  }
}

class Person {
  Integer personId;
  ArrayList<Person> friends;
}

class Machine {
  int machineId;
  HashMap<Integer, Person> persons;
  Person getPersonById(Integer personId) {
    return persons.get(personId);
  }
}