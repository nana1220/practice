/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
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

// maintain two lists, small list and large list
public class Solution {
  public ListNode partition(ListNode head, int x) {
    ListNode small = new ListNode(0);
    ListNode big = new ListNode(0);
    ListNode s = small;
    ListNode b = big;
    ListNode curr = new ListNode(0);
    curr.next = head;
    while(curr.next != null){
      if(curr.next.val >=x){
        b.next = curr.next;
        b = b.next;
      } else {
        s.next = curr.next;
        s = s.next;
      }
      curr = curr.next;
    }
    s.next=null; // dont forget to set null to list end, otherwise cycle occurs
    b.next=null;
    if(big.next == null) return small.next;
    if(small.next == null) return big.next;
    s.next = big.next;
    return small.next;
  }
}