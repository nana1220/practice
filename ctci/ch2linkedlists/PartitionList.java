package ch2linkedlists;

import java.lang.Integer;
import java.lang.System;
import java.util.ArrayList;

public class PartitionList {
  public static void main(String[] args) {
    ListNode<Integer> head = ListNode.buildList(new Integer[] {10, 9, 8, 1, 2, 3, 4, 5});
    ListNode<Integer> res1 = partitionList1(head, 4);
    System.out.println(res1.toString());

//    head = ListNode.buildList(new Integer[] {10, 9, 8, 1, 2, 3, 4, 5});
    ListNode<Integer> res2 = partitionList2(head, 4);
    System.out.println(res2.toString());
  }

  /*
   * use two buffers, one storing nodes of values smaller than x
   * another storing nodes of values bigger than or equal to x.
   */
  static ListNode<Integer> partitionList1(ListNode<Integer> list, Integer x) {
    ArrayList<ListNode<Integer>> smallBuffer = new ArrayList<ListNode<Integer>>();
    ArrayList<ListNode<Integer>> bigBuffer = new ArrayList<ListNode<Integer>>();
    ListNode<Integer> head = list;
    while (head != null) {
      if (head.data >= x) {
        bigBuffer.add(head);
      } else {
        smallBuffer.add(head);
      }
      head = head.next;
    }
    for (int i = 0; i < smallBuffer.size() - 1; i++) {
      smallBuffer.get(i).next = smallBuffer.get(i + 1);
    }
    for (int i = 0; i < bigBuffer.size() - 1; i++) {
      bigBuffer.get(i).next = bigBuffer.get(i + 1);
    }
    smallBuffer.get(smallBuffer.size() - 1).next = bigBuffer.get(0);
    bigBuffer.get(bigBuffer.size() - 1).next = null;
    head = smallBuffer.get(0);
    return head;
  }

  /*
   * use two linked list one for small values, one for large values, then merge together
   */
  static ListNode<Integer> partitionList2(ListNode<Integer> list, Integer x) {
    if (list == null || list.next == null) {
      return list;
    }
    ListNode<Integer> smallHead = null;
    ListNode<Integer> smallCurr = null;
    ListNode<Integer> bigHead = null;
    ListNode<Integer> bigCurr = null;
    ListNode<Integer> curr = list;
    while (curr != null) {
      // Note: can set next = null before a node is added to the list,
      // because the added one is currently the last one in the list
      // ListNode<Integer> next = curr.next;
      // curr.next = null;
      if (curr.data < x) {
        if (smallHead == null) {
          smallHead = curr;
          smallCurr = curr;
        } else {
          smallCurr.next = curr;
          smallCurr = smallCurr.next;
        }
      } else {
        if (bigHead == null) {
          bigHead = curr;
          bigCurr = curr;
        } else {
          bigCurr.next = curr;
          bigCurr = bigCurr.next;
        }
      }
      curr = curr.next; // curr = next;
    }
    // Note: must check if linked list is null
    if (smallHead == null) { // Don't forget
      bigCurr.next = null;
      return bigHead;
    } else if (bigHead == null) { // this is not necessary, null can be assigned to smallCurr.next
      smallCurr.next = null;
      return smallHead;
    } else {
      bigCurr.next = null;
      smallCurr.next = bigHead;
      return smallHead;
    }
  }
}