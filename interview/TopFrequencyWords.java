/*
bob, joe, bob, jane, bob, joe, jack
bob = 3
joe = 2

topN(2) = bob, joe

interface TopN {

  void insert(String query);

  List<String> getTop(int n);
}
 */

class MyTopN implements TopN {
  // if use priorityQueue, no way to update frequency for items in the queue
  class Node {
    int freq;
    String word;
    Node next;
    Node prev;
  }

  // HashMap<word, doubleLinkedListNode>
  class MyTopN implements TopN {
    HashMap<String, Node> words;
    Node head;
    Node tail;

    // worst case O(n)
    void insert(String query) {
      Node res = words.get(query);
      if (res == null) {
        res = new Node(query, 1);
        words.put(query, res);
        tail.next = res;
        tail = res;
      } else {
        res.freq++;
        bubbleUp(res);
      }
    }
    // O(k)
    List<String> getTop(int n) {
      ArrayList<String> res = new ArrayList<String>();
      Node curr = head;
      while (curr != null && n > 0) {
        res.add(curr.word);
        curr = curr.next;
        n--;
      }
      return res;
    }

    void bubbleUp(Node curr) {
      while (curr.prev != null && curr.prev.freq < curr.freq) {
        Node pre = curr.prev;
        Node ne = curr.next;
        pre.next = ne;
        ne.prev = pre;
        curr.next = pre;
        pre.prev = curr;
        if (pre.prev != null) {
          pre.prev.next = curr;
          curr.prev = pre.prev;
        }
      }
    }
  }