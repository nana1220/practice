package ch10scalabilityandmemorylimits;

import java.util.HashMap;
/*
  Imagine a web server for a simplified search engine. This system has 100 machines to
respond to search queries,which may then call out using processSearch (string
query) to another cluster of machines to actually get the result. The machine
which responds to a given query is chosen at random, so you can not guarantee that
the same machine will always respond to the same request. The method processSearch
is very expensive. Design a caching mechanism to cache the results of the
most recent queries. Be sure to explain how you would update the cache when data
changes.
 */


/*
 * Single machine: least-recently used (LRU) cache
 * put the query result in both hash table for fast query and linked list for erasing
 * old data and moving "fresh" data to the front, so that when cache reaches to the max size,
 * the tail of liked list will be removed
 */
public class SearchCaching {
  static int CACHE_SIZE = 10;
  Node head;
  Node tail;
  HashMap<String, Node> map;
  int currSize = 0;

  SearchCaching() {
    map = new HashMap<String, Node>();
  }

  // assume if node is not null node must be in the list
  void removeNode(Node node) {
    if (node == null) return;
    if (node.next != null || node.prev != null) { // Note: need to first check if node is in the list!!
      currSize--;
    }
    if (node.prev != null) { // Note: always check first!!
      node.prev.next = node.next;
    }
    if (node.next != null) { // Note: always check first!!
      node.next.prev = node.prev;
    }
    if (node == head) { // Note: after link the prev and next, then check if node is head or tail
      head = node.next;
    }
    if (node == tail) {
      tail = node.prev;
    }
    // don’t forget to set prev and next to null
    node.next = null;
    node.prev = null;
    currSize--; // don’t forget
  }

  // either node is in the list or not in the list
  void moveToFront(Node node) {
    if (node == head) return;
    removeNode(node); // removeNode will handle if node in the list or not
    if (head != null) { // Note!! Don’t forget to check if a node is null before call node.prev or node.next
      head.prev = node;
    }
    node.next = head;
    head = node;
    currSize++;
  }

  void moveToFront(String query) {
    moveToFront(map.get(query));
  }

  String[] getResults(String query) {
    if (!map.containsKey(query)) return null;
    Node node = map.get(query);
    moveToFront(node); // Don’t forget to move node to front before return result
    return node.results;
  }

  void insertResults(String query, String[] results) {
    if (!map.containsKey(query)) {
      Node node = new Node(query, results); // create new node for linkedlist
      map.put(query, node); // also put it in hashmap
    }
    Node node = map.get(query);
    node.results = results; // if query result exists, refresh result
    moveToFront(node); // in either case move to front
    if (currSize > CACHE_SIZE) {
      removeNode(tail); // remove from linkedlist
      map.remove(tail.query); // remove from map
    }
  }

  static class Node {
    public Node prev;
    public Node next;
    public String[] results;
    public String query;

    public Node(String q, String[] res) {
      results = res;
      query = q;
    }
  }
}