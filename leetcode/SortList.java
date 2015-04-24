/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// O(nlg(n)), so merge sort or heap sort, since heap sort needs random access to the element in array
// use merge sort
// remember to cut left half list
public class Solution {
  public ListNode sortList(ListNode head) {
    if(head==null || head.next==null) return head;
    ListNode slow =new ListNode(0);
    slow.next=head; // use dummy node to make slow the previous node of right half list
    ListNode fast = head;
    while(fast!=null && fast.next!=null){
      fast=fast.next.next;
      slow=slow.next;
    }
    ListNode rHead = slow.next;
    slow.next=null;// NOTE!!!: don't forget to cut left half list
    ListNode left = sortList(head);
    ListNode right = sortList(rHead);
    ListNode res = merge(left,right);
    return res;
  }
  ListNode merge(ListNode l, ListNode r){
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    while(l!=null && r!=null){
      if (l.val<=r.val){
        curr.next=l;
        curr=curr.next;
        l=l.next;
      } else{
        curr.next = r;
        curr=curr.next;
        r=r.next;
      }
    }
    if(l!=null) curr.next=l;
    if(r!=null) curr.next =r;
    return dummy.next;
  }
}