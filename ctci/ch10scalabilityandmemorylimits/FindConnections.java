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
public class Server {
  HashMap<Integer, Machine> machines = new HashMap<Integer, Machine>();
  HashMap<Integer, Integer> personToMachineMap = new HashMap<Integer, Integer>();

  public Machine getMachineWithId(int machineID) {
    return machines.get(machineID);
  }

  public int getMachineIDForUser(int personID) {
    Integer machineID = personToMachineMap.get(personID);
    return machineID == null ? -1 : machineID;
  }

  public Person getPersonWithID(int personID) {
    Integer machineID = personToMachineMap.get(personID);
    if (machineID == null) {
      return null;
    }
    Machine machine = getMachineWithId(machineID);
    if (machine == null) {
      return null;
    }
    return machine.getPersonWithID(personID);
  }
}

public class Person {
  private ArrayList<Integer> friends;
  private int personID;
  private String info;

  public String getInfo() { return info; }
  public void setInfo(String info) {
    this.info = info;
  }

  public int[] getFriends() {
    int[] temp = new int[friends.size()];
    for (int i = 0; i < temp.length; i++) {
      temp[i] = friends.get(i);
    }
    return temp;
  }
  public int getID() { return personID; }
  public void addFriend(int id) { friends.add(id); }

  public Person(int id) {
    this.personID = id;
  }
}

class Machine {
  int machineId;
  HashMap<Integer, Person> persons;
  Person getPersonById(Integer personId) {
    return persons.get(personId);
  }
}