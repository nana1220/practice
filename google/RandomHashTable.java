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

/*
我们需要一个vector和一个hash table。vector存实际的数据，hash table存每个value对应在数组里面的index。
add操作要把新插进去的value的id保存到hash table里。
remove操作首先根据给的value在hash中查出所在id，然后把数组最后一个元素move到要删除的这个元素所在的位置。然后更新index和size。
random返回只需要一个简单的rand() % size就好。
注意这个solution是无法处理重复元素的。
 */