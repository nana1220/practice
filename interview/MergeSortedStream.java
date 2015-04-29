/*
输入为一个Iterator数组，这些Iterator分别取出来的数都是已排序的，设计并实现一个MergeIterator类，merge这些sorted iterator。
你的MergeIterator类需要包含has_next和get_next方法。
注意，Iterator也只包含has_next和get_next方法。
 */

// 实际上就是多路归并，用一个heap就可以搞定，
public class MergeIterator implements Iterator<Integer> {
  public MinHeap h = new MinHeap();

  class Node { int v; Iterator<Integer> i; } // can store input iterators in an array, store wrap value and iter index together and put in mean heap

  public MergeIterator(Collection<Iterator<Integer>> inputs) {
    for(Iterator<Integer> i : inputs) {
      if(i.hasNext())
        h.insert(new Node(i.next(), i));
    }
  }
  public boolean hasNext() { return ! h.isEmpty(); }
  public Integer next() {
    Node min = h.extractMin();
    if(min.i.hasNext())
      h.insert(new Node(min.i.next(), min.i));
    return min.v;
  }
}