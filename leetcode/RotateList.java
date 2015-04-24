/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
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
  public ListNode rotateRight(ListNode head, int k) {
    if(head==null) return head;
    ListNode dummy=new ListNode(0);
    dummy.next=head;
    ListNode tmp = head;
    int len=0;
    while(tmp !=null){
      len++;
      tmp=tmp.next;
    }
    k = k%len;
    if(k==0) return head; // note this edge case!!!!!!!!!
    ListNode tail=head;
    while(k >1){
      tail= tail.next;
      k--;
    }
    while(tail.next!=null){
      tail=tail.next;
      dummy=dummy.next;
    }
    tail.next =head;
    ListNode newHead = dummy.next;
    dummy.next=null;
    return newHead;

  }

}