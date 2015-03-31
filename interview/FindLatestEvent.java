/*
A session is a series of Events. Each session is identified by a number, the sessionId. Events have
this structure:

class Event {
  public int sessionId;  // identities the session this Event belongs to
  public int timestamp;  // time the Event occurred
  [...]
}

We have an unordered list of Events from multiple sessions intermixed. Given this list (List<Event>),
construct a new list containing only the latest Event for each session.
 */

import java.lang.Integer;
import java.util.*;
import java.util.HashMap;

public class FindLatestEvent {

  class Event {
    public int sessionId;  // identities the session this Event belongs to
    public int timestamp;  // time the Event occurred
  }


  /*
   * sort by primary key and  secondary key
   * nlog(n)
   */
  List<Event> makeList1(List<Event> list) {
    Comparator<Event> comp = new Comparator<Event>() {
      int compare(Event e1, Event e2) {
        if (e1.sessionId ! = e2.sessionId)return e1.sessionId - e2.sessionId;
        else return e2.timestamp - e1.timestamp; // first one is with latest timestamp
      }
    };
    Collections.sort(list, comp);
    int id = list.sessionId;
    List<Event> curr = list;
    List<Event> head = list;
    while (list != null) {
      if (list.sessionId != id) {
        id = list.sessionId;
        curr.next = list;
        curr = list;
      }
      list = list.next;
    }
    return head;
  }

  /*
   * use hashtable, O(n)
   * eventId is unique, used as key, timestamp is value
   */
  List<Event> makeList2(List<Event> list) {
    Map<Integer, Integer> table = new HashMap<Integer, Integer>();
    while (list != null) {
      if (!table.containsKey(list.sessionId)) {
        table.put(list.sessionId, list.timestamp);
      } else {
        if (table.get(list.sessionId).timestamp < list.timestamp) {
          table.put(list.sessionId, list.timestamp);
        }
      }
    }
    List<Event> res = new List<Event>();
    List<Event> curr = new List<Event>();
    for (int key : table.keySet()) {
      List<Event> tmp = new List<Event>(key, table.get(key));
      curr.next = tmp;
      curr = curr.next;
    }
    return res.next;
  }
}