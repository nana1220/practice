/*
 * Given a Collection class, implement its iterator with peek()
 *
 * interface Iterator<E>{
 * boolean hasNext();
 * E next();
 * void remove();
 * }
 */

import java.lang.Exception;
import java.util.Iterator;

public class ImplementIteratorWithPeek {

  public class ArrayList<E> implements Iterable<E> {
    private Object[] elems;
    private int size;

    public Iterator<E> iterator() {
      return new Iter<E>();
    }

    public class Iter<E> implements Iterator<E> { // recall last returned, and peek next
      int nextIdx;
      int lastIdx = -1;

      public boolean hasNext() {
        return nextIdx < size;
      }

      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        lastIdx = nextIdx;
        return (E) elems[nextIdx++];
      }

      public E peek() {
        if (!hasNext()) {
          return null;
        }
        return (E) elems[nextIdx];
      }

      // remove last, v is the position of iterator: 1,v 2,3 -> 2,3, 1,2,v,3 -> 1,3
      public void remove() {
        if (lastIdx < 0) throw new Exception();
        System.arraycopy(elems, nextIdx, elems, lastIdx, size - nextIdx);
        nextIdx = lastIdx;
        lastIdx = lastIdx - 1;
        size--;
      }
    }
  }
}