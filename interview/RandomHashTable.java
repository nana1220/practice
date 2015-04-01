/*
Implement HashTable with get,set,delete,getRandom functions in O(1).
 */


public class HashTable {
  HashMap<String, Integer> items;
  ArrayList<String> keys; // for getting random value in O(1)
  HashMap<String, Integer> keyToIndex; // for fast deletion in O(1)

  int get(String key) {
    return items.get(key);
  }

  void put(String key, Intege val) {
    items.put(key, val);
    keys.add(key);
    keyToIndex.put(key, keys.size() - 1);
  }

  void delete(String key) {
    items.remove(key);
    int idx = keyToIndex.get(key);
    keyToIndex.remove(key);
    String k = keys.get(keys.size() - 1);
    keys.set(idx, k);
    keys.remove(keys.size() - 1);
  }

  int getRandom() {
    int idx = new Random().nextInt(keys.size());
    String key = keys.get(idx);
    return items.get(key);
  }
}