public class Hash<K, V> {
  private final int MAX_SIZE = 5;
  LinkedList<Cell<K, V>>[] items;

  public Hash() {
    items = (LinkedList<Cell<K, V>>[]) new LinkedList[MAX_SIZE];
  }

  public int hashCodeOfKey(K key) {
    return key.toString().length() % items.length;
  }

  public void put(K key, V value) {
    int x = hashCodeOfKey(key);
    if (items[x] == null) {
      items[x] = new LinkedList<Cell<K, V>>();
    }
    LinkedList<Cell<K, V>> collided = items[x];
    for (Cell<K, V> c : collided) {
      if (c.equivalent(key)) {
        collided.remove(c);
        break;
      }
    }

    Cell<K, V> cell = new Cell<K, V>(key, value);
    collided.add(cell);
  }

  public V get(K key) {
    int x = hashCodeOfKey(key);
    if (items[x] == null) {
      return null;
    }
    LinkedList<Cell<K, V>> collided = items[x];
    for (Cell<K, V> c : collided) {
      if (c.equivalent(key)) {
        return c.getValue();
      }
    }
    return null;
  }
}

public class Cell<K, V> {
  private K key;
  private V value;
  public Cell(K k, V v) {
    key = k;
    value = v;
  }
}