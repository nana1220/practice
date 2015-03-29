/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
You may not alter the values in the nodes, only nodes itself may be changed.
Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
 */

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class ReverseNodesInKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) return null;
    ListNode res = new ListNode(0);
    res.next = head; // Don't forget
    ListNode prev = res;
    while (prev.next != null) {
      ListNode check = prev;
      int count = 0;
      for (; count < k; count++) {
        if (check.next == null) break; // NOTE: need to check here k times
        // NOTE:!!!!!when reach the k-th node, still need a loop to check if k-th is null
        check = check.next;
      }
      if (count < k) {
        break; // not enough nodes
      }
      prev = reverseK(prev, k);
    }
    return res.next;
  }

  // reverse k nodes, input prev always point to the previous node of the first node of original list
  // return cur should point to last node of reversed list
  // NOTE: Always maintain a pointer pointing to the head node of the group, and two other
  // pointers to the swap pairs. Each time swap two nodes, k nodes group swap k - 1 times.
  // Every swap consider three nodes: prev node of the group, the two swap nodes
  ListNode reverseK(ListNode prev, int k) {
    // left always points to the head node of original node group
    // each loop left swap with a new node, in the end left becomes the end node of the group
    ListNode left = prev.next;
    for (int i = 0; i < k - 1; i++) {
      ListNode right = left.next;
      left.next = right.next;
      right.next = prev.next; // NOTE: use prev.next not left
      prev.next = right;
    }
    return left;
  }
}