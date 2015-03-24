package basics;

/*
 * implement hashtable
 */

public class HashTable<K, V> {

  private final int TABLE_SIZE =10;
  private Entry[] items;

  public HashTable() {

    items = new Entry[TABLE_SIZE];
  }

  public int hashCode(K key) {
    return key.toString().length() % TABLE_SIZE;
  }

  public void put(K key, V val) {
    int idx = hashCode(key);
    // Note: Don’t forget to check if a slot on array has not been filled
    if (items[idx] == null) {
      items[idx] = new Entry(key, val);
    }
    Entry item = items[idx];
    while (true) {
      if (key.equals(item.key)) {
        item.value = val;
        return;
      }
      if (item.next != null) {
        item = item.next;
      } else {
        item.next = new Entry(key, val);
        break;
      }
    }
  }

  public V get(K key) {
    int idx = hashCode(key);
    Entry item = items[idx];
    while (item != null) {
      if (key.equals(item.key))
        return (V) item.value; // dont' forget to cast
      else item = item.next;
    }
    return null;
  }

  /*
   * linked list
   * Note: if not declare static, generics array creation error will throw when items = new Entry[TABLE_SIZE];
   */
  public static class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    Entry(K k, V v) {
      key = k;
      value = v;
      next = null;
    }
  }
}