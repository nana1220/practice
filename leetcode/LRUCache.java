/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the
following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached
its capacity, it should invalidate the least recently used item before inserting a new item.
 */



// First!!!!!!!!!!!!: don't forget set prev pointer for double linked list
// Second:!!!!! handle the case when remove node list becomes empty, and when add first node to empty list in which case head and tail is null
// Third!!!!: when move tail to head need to check if tail happen to be head, that is size of list is 1, in which case tail.prev is null
public class LRUCache {
  HashMap<Integer, ListNode> data;
  ListNode head;
  ListNode tail;
  int capacity;
  int size;
  public LRUCache(int capacity) {
    data = new HashMap<Integer, ListNode>();
    head=null;
    tail=null;
    this.capacity = capacity;
    size=0;
  }

  public int get(int key) {
    ListNode node = data.get(key);
    if(node==null) return -1;
    moveToHead(node);
    return node.val;
  }

  public void set(int key, int value) {
    ListNode node = data.get(key);
    if(node!=null){
      node.val = value;
      moveToHead(node);
    } else{
      if(size==capacity) { //NOTE:: also need to consider if list becomes empty after remove tail, that is capacity is 1
        data.remove(tail.key);
        if(capacity > 1){
          tail=tail.prev;
          tail.next=null;
        } else{ // list is empty
          head =null;
          tail=null;
        }
        size--;
      }
      node = new ListNode(key,value);
      data.put(key, node);
      node.next=head;
      // NOTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! doule linked list don't forget to set prev
      if (head!=null) { // need to check!!!!!!!!!!!!!!!!!!!, if list is empty, head is null
        head.prev = node; // took me 40 mins to find this bug!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      }
      head=node;
      size++;
      if(size==1) tail=node; // NOTE!!!!!!!!: dont forget to set tail when add first node
    }

  }

  void moveToHead(ListNode node){
    if(node!=head && node!=tail){
      node.prev.next = node.next;
      node.next.prev=node.prev;
      node.prev=null;
      node.next=head;
      head.prev = node;  // NOTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! doule linked list don't forget to set prev
      head=node;
    } else if(node==tail && node!=head){ // NOTE!!!: also need to check node is not head
      tail=node.prev;// need to ensure node is not head, in the case of size 1, head and tail are same
      tail.next = null;
      node.prev=null;
      node.next=head;
      head.prev = node; // NOTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! doule linked list don't forget to set prev
      head=node;
    }
  }

  public class ListNode{
    int key; //NOTE!!!!!: store key in node, so that we can find the key given node, and remove node from hashmap
    int val;
    ListNode prev;
    ListNode next;
    public ListNode(int key, int val){
      this.key=key;
      this.val = val;
      next=null;
      prev=null;
    }
  }
}

// TLE:  use built-in linked list store key and hashmap for key value
// NOTE:!!!!! the queue is in fact only needs to store keys, and hashmap for key values
// so the above implementation can be HashMap<key, value>, ListNode<key>,
// NONONO, if not put node in hashmap, cannot find node in O(1), so the moveToTop operation in linkedlist becomes O(n)
public class LRUCache {
  HashMap<Integer, Integer> items;
  LinkedList<Integer> keys;
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
        items.remove(keys.poll());
      }
      keys.add(key);
    } else {
      keys.remove(new Integer(key));
      keys.add(key);
    }
    items.put(key, value);
  }
}