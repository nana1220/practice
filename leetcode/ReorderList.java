/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

/*
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// reverse right half of the list, then interleave left half and right half, if length is odd, left half has one more  node
public class Solution {
  public void reorderList(ListNode head) {
    if (head == null) return;
    ListNode lEnd = new ListNode(0);
    lEnd.next = head;
    // fast and slow both point to dummy head, thus slow go to last node of left half when loop ends, no matter odd of even length
    ListNode fast = lEnd;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      lEnd = lEnd.next;
    }
    ListNode rHead = lEnd.next;
    lEnd.next = null; // cut list
    rHead = reverse(rHead);
    ListNode lHead = head;
    ListNode curr = new ListNode(0);
    curr.next = lHead;
    head = curr;
    while (lHead != null && rHead != null) {
      curr.next = lHead;
      lHead = lHead.next;
      curr.next.next = rHead;
      rHead = rHead.next;
      curr = curr.next.next;
    }
    curr.next = lHead; // left half may have one more node, otherwise add null to list end doesn't matter
    head = head.next;
  }

  // reverse list
  ListNode reverse(ListNode head) {
    if (head == null) return null;
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode curr = head;
    while (curr.next != null) {
      ListNode next = curr.next;
      curr.next = next.next;
      next.next = dummyHead.next;
      dummyHead.next = next;
    }
    return dummyHead.next;
  }

  // recursivly reverse
  public ListNode reverse(ListNode head) {
    if (head == null) return null;
    if (head.next == null) return head;
    // in Lisp this is easy, but we don't have cons
    // so we grab the second element (which will be the last after we reverse it)
    ListNode tail = head.next; // tail
    // need to unlink list from the rest or you will get a cycle
    head.next = null;
    // then we reverse everything from the second element on
    ListNode reverseRest = reverse(tail);
    // then we join the two lists
    tail.next = head;
    return reverseRest;
  }
}

// recursion, relocate the last node in each recursive call
// time: O(n^2); space: recursive stack
public class Solution {
  public void reorderList(ListNode head) {
    if (head == null || head.next == null)
      return;
    ListNode pp = head, p = head.next;
    while (p.next != null) { // find the last two nodes of the list
      pp = pp.next;
      p = p.next;
    }
    pp.next = null;// cut at the second node to the last
    p.next = head.next; // move last to head.next
    head.next = p;
    reorderList(p.next); // recursion
  }
}