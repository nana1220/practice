/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the
following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached
its capacity, it should invalidate the least recently used item before inserting a new item.
 */

import java.util.*;

// Time Limit Exceeded
// TODO: write double linked class
public class LRUCache {
  HashMap<Intege, Integer> items; // HashMap<Integer, DNode> items;
  LinkedList<Integer> keys; //  TODO: implement a double linked list, DNode head; DNode tail;
  int capacity;

  public LRUCache(int capacity) {
    items = new HashMap<Integer, Integer>();
    keys = new LinkedList<Integer>();
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!items.containsKey(key)) {
      return -1;
    }
    keys.remove(new Integer(key));
    keys.add(key);
    return items.get(key);
  }

  public void set(int key, int value) {
    if (!items.containsKey(key)) {
      if (keys.size() == capacity) {
        items.remove(keys.poll()); // NOTE: don't forget remove item in hashmap
      }
      keys.add(key);
    } else {
      keys.remove(new Integer(key));
      keys.add(key);
    }
    items.put(key, value);
  }
}