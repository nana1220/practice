package ch2linkedlists;

import java.lang.System;

public class DeleteMiddleNode {
  public static void main(String[] args) {
    ListNode<String> head = ListNode.buildList(new String[] {"a", "b", "c", "d", "e"});
    ListNode<String> middle = head.next.next;
    deleteNode1(middle);
    System.out.println(head.toString());
  }

  /*
   * copy the value of next node to current node
   */
  public static <E> void deleteNode1(ListNode<E> middleNode) {
    if (middleNode == null || middleNode.next == null)
      return;
    middleNode.data = middleNode.next.data;
    middleNode.next = middleNode.next.next;
  }

  /*
   * pointer to middle node is not available, so copy value of next node to current node
   * until the end of list. Or simply copy the value of next node to current node then
   * delete next node.
   * Note: if the node to be deleted is the last node, then problem cannot be solved
   * since there is no way to make previous node's next pointer null. Consider making this node
   * as dummy instead of deleting it.
   */
  public static <E> void deleteNode2(ListNode<E> middleNode) {
    if (middleNode == null || middleNode.next == null) {
      return;
    }
    ListNode<E> middle = middleNode;
    ListNode<E> next = middle.next;
    while (next.next != null) {
      middle.data = next.data;
      middle = middle.next;
      next = next.next;
    }
    middle.data = next.data; // Note: Don't forget to copy last node
    middle.next = null; // the next node (i.e. now the last node) is cut off the list
  }
}