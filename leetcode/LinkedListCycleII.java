/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
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
// TLE for large data set
public class Solution {
  public ListNode detectCycle(ListNode head) {
    ListNode slow=head;
    ListNode fast=head;
    while(fast!=null&&fast.next!=null){
      fast=fast.next.next;
      slow=slow.next;
      if(fast==slow) break;
    }
    if(fast==null|| fast.next==null) return null; // remember to check non cycle here
    while(head!=slow){
      head=head.next;
      slow=slow.next;
    }
    return head;
  }
}
