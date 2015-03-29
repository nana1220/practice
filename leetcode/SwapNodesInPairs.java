/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.
Your algorithm should use only constant space. You may not modify the values in the list,
only nodes itself can be changed.
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
public class SwapNodesInPairs {
  /*
   * create a dummy head
   * maintain 3 pointers, curr points to the node before pair, two points each pointing to
   * a node in pair.
   * If one of the pair nodes is null, no need to swap, return res
   */
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode res = new ListNode(0);
    ListNode curr = res;
    curr.next = head;
    while (curr.next != null && curr.next.next != null) {
      ListNode left = curr.next;
      ListNode right = curr.next.next;
      curr.next = right;
      left.next = right.next;
      right.next = left;
      curr = left;
    }
    return res.next; // Dont forget next, return dummyHead.next not just dummyHead
  }
}
