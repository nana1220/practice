/*
Program an iterator for a List which may include nodes or List
{0, {1, 2}, 3, {4, {5, 6}}} Iterator returns 0 1 2 3 4 5 6
 */

public class FlattenIterator implements Iterator<Integer> {
  stack<Iterator> st;
  Object next;

  public FlattenIterator(LinkedList<Object> in) {
    if (in != null) {
      st = new Stack<Iterator>();
      st.push(in.iterator());
    }
  }

  private void moveToNext() {
    next = null;
    while (!st.isEmpty()) {
      Iterator it = st.peek();
      if (it.hasNext()) {
        Object o = it.next();
        if (o instanceof Integer) {
          next = (Integer) o;
          break;
        }
        if (o instanceof List) {
          st.push((List) o.iterator());
        }
      } else {
        st.pop();
      }
    }
  }

    public boolean hasNext () {
      next = null;
      moveToNext();
      return next != null;
    }
    public Integer next ()
    {
      if (next == null) throw NoSuchElementException;
      return next;
    }
  }
}




class MyIterator implements Iterator<Integer> {

  Node n1; //traverses main nodes
  Node n2; //traverses nested nodes

  boolean traverseNestedNodes;

  MyIterator(List list) {
    n1 = list.start;
    n2 = null;
  }

  @Override
  public boolean hasNext() {
    return n2 != null || n1 != null;
  }

  @Override
  public Integer next() {
    int value;
    if (n1 == null && n2 == null) {
      throw new NoSuchElementException();
    }

    if (n2 == null) {
      value = n1.value;
      n2 = n1.nestedNodes;
      n1 = n1.next;
    } else {
      value = n2.value;
      n2 = n2.next;
    }

    return value;

  }

  @Override
  public void remove() {

  }
}

class Node {
  int value;
  Node next;

  Node nestedNodes;

  Node(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value + "";
  }

  Node addNextNode(int value) {
    Node nextNode = new Node(value);
    this.next = nextNode;
    return nextNode;
  }

  Node addNestedNode(int value) {
    Node newNode = new Node(value);
    if (nestedNodes == null) {
      nestedNodes = newNode;
    } else {

      Node cursor = nestedNodes;
      while (cursor.next != null) {
        cursor = cursor.next;
      }
      cursor.next = newNode;
    }
    return this;
  }

}

class List {
  Node start;

  List(Node start) {
    this.start = start;
  }

  @Override
  public String toString() {
    Node current = start;
    StringBuilder buf = new StringBuilder();
    while (current != null) {
      buf.append(current + "  ");
      current = current.next;
    }

    return buf.toString();
  }

  Iterator<Integer> iterator() {
    return new MyIterator(this);
  }
}

public class Test {

  public static void main(String[] args) {

    Node start = new Node(1);

    start.addNextNode(2).addNestedNode(3).addNestedNode(4).addNextNode(5)
        .addNestedNode(6);

    List list = new List(start);
    Iterator<Integer> t = list.iterator();

    while (t.hasNext()) {
      System.out.print(t.next() + "  ");
    }

  }

}
