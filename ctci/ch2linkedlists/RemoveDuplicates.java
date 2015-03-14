package ch2linkedlists;

import java.util.HashSet;

public class RemoveDuplicates {

  public static void main(String[] args) {
    ListNode<String> list1 = ListNode.buildList(args);
    list1 = removeDuplicates1(list1);
    System.out.println(list1.toString());
    ListNode<String> list2 = ListNode.buildList(args);
    list2 = removeDuplicates2(list2);
    System.out.println(list2.toString());
  }

  /*
   * traverse all nodes, maintain two pointers, no buffer needed.
   * time: O(n^2); space O(1)
   */
  static <E> ListNode<E> removeDuplicates1(ListNode<E> list) {
    if (list == null || list.next == null) {
      return list;
    }
    ListNode<E> head = list; // keep the head of list
    while (head != null) {
      ListNode<E> p = head;
      while (p.next != null) {
        if ((p.next.data).equals(head.data)) {
          p.next = p.next.next;
        } else {
          // Note: if no else, p will also be set to p.next.next,
          // then in the next loop p.next becomes p.next.next.next
          p = p.next;
        }
      }
      head = head.next;
    }
    return list; // return head
  }

  /*
   * use a HashSet<nodeData> as a buffer marking the appearance of the node
   * time: O(n)
   */
  static <E> ListNode<E> removeDuplicates2(ListNode<E> list) {
    if (list == null || list.next == null) {
      return list;
    }
    HashSet<E> marker = new HashSet<E>();
    ListNode<E> head = list;
    marker.add(head.data);
    while (head.next != null) {
      if (marker.contains(head.next.data)) {
        head.next = head.next.next;
      } else {
        marker.add(head.next.data);
        head = head.next;
      }
    }
    return list;
  }
}