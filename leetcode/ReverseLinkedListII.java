/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
public class Solution {
  public ListNode reverseBetween(ListNode head, int m, int n) {
    // if(m==n) return head;
    ListNode dummy = new ListNode(0);
    ListNode l = dummy;
    dummy.next = head;
    int k = n-m;
    while(m-- >1){
      l = l.next; // dummy move (m-1) step pointing to (m-1)-th node
    }
    ListNode mid = l.next;
    ListNode r = mid.next;
    for(int i=0; i<k; i++) {// for n-m+1 nodes, only need reverse n-m times, NOTE!!!!, m has been changed, so use k record n-m first
      mid.next = r.next;
      r.next = l.next; // r.next = mid; NOTE!!!!! use l.next instead of mid, because mid keeps shifting right while l.next always points to m-th node
      l.next=r;
      r=mid.next;
    }
    return dummy.next; // use dummy pointer, because head could be changed
  }
}