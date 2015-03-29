/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,
   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
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
public class RemoveNthNodeFromEndofList {
  // make left one node before the node that needs to be removed
  // NOTE: if there is only one node in the list, cannot make left one node
  // before it without creating a new node, to guard this edge case,
  // create a new node and make the head of input as its next node
  // then if head is the only node, left is pointing to the node before it
  public ListNode removeNthFromEnd(ListNode head, int n) {
    // NOTE: maintain root, and return root.next in the end, don't return head, head could be removed
    ListNode root = new ListNode(0);
    ListNode left = root;
    ListNode right = root;
    root.next = head;
    for (int i = 0; i < n; i++) { // left call n next reach right
      right = right.next;
    }
    while(right.next != null) { // when right reach the end node, left is the node before the node to be removed
      left = left.next;
      right = right.next;
    }
    ListNode res = left.next;
    left.next = left.next.next;
    return root.next;
  }
}