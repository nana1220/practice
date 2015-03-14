package ch2linkedlists;

import java.lang.System;

public class FindKthToLast {
  public static void main(String[] args) {
    ListNode<String> list1 = ListNode.buildList(args);
    list1 = findNode1(list1, 5);
    System.out.println(list1.data);

    ListNode<String> list2 = ListNode.buildList(args);
    findNode2(list2, 5);

    ListNode<String> list3 = ListNode.buildList(args);
    list3 = findNode3(list3, 5, new Counter());
    System.out.println(list3.data);

    ListNode<String> list4 = ListNode.buildList(args);
    list4 = findNode1(list4, 5);
    System.out.println(list4.data);
  }

  /*
   * non-recursive
   * first find out list size, the k-th to last is first (size - k)-th (0-based)
   */
  static <E> ListNode<E> findNode1(ListNode<E> list, int k) {
    if (list == null) {
      return null;
    }
    ListNode<E> curr = list;
    int size = 1;
    while (curr.next != null) {
      size++;
      curr = curr.next;
    }
    if (size  < k) {
      return null;
    }
    curr = list;
    for (int i = 0; i < size - k; i++) {
      curr = curr.next;
    }
    return curr;
  }

  /*
   * recursive method 1, return count
   * can also create a class that stores both count and node, then return instance of this class
   */
  static <E> int findNode2(ListNode<E> head, int k) {
    if (head == null) {
      return 0;
    }
    int i = findNode2(head.next, k) + 1;
    if (i == k) {
      System.out.println(head.data);
    }
    return i;
  }

  /*
   * recursive method 2, return node, create a wrapper for count then pass count by reference
   * Another approach is use a static variable for count
   */
  static <E> ListNode<E> findNode3(ListNode<E> head, int k, Counter counter) {
    if (head == null) {
      return null;
    }
    ListNode<E> res = findNode3(head.next, k, counter);
    counter.count++;
    if (counter.count == k) {
      return head;
    }
    return res;
  }
  static class Counter {
    int count = 0;
  }

  /*
   * maintain two points, one is k nodes ahead of the other
   * time: O(n), space: O(1)
   */
  static <E> ListNode<E> findNode3(ListNode<E> list, int k) {
    ListNode<E> ahead = list;
    ListNode<E> behind = list;
    for (int i = 0; i < k; i++) {
      ahead = ahead.next;
    }
    while (ahead != null) {
      ahead = ahead.next;
      behind = behind.next;
    }
    return behind;
  }
}