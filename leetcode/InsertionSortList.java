/*
Sort a linked list using insertion sort.
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
  public ListNode insertionSortList(ListNode head) {
    if(head==null) return null;
    ListNode dummy = new ListNode(0);
    dummy.next =head;
    ListNode left = dummy; // left for inner loop, before every inner loop set left to dummy head
    ListNode curr = head; // curr for outter loop
    while(curr.next!=null){ // maintain pointer to previous node, so that we can have pointer to the node needs to be inserted
      left = dummy; // NOTE!!!!!!: don't forget to set start point of inner loop
      while(left.next != curr.next && left.next.val <= curr.next.val){
        left = left.next;
      }
      if(left.next.val > curr.next.val){// if swap node, don't update curr
        ListNode small = curr.next;
        curr.next = curr.next.next;
        small.next = left.next;
        left.next = small;
        if(dummy==left) dummy.next=small; // NOTE!!: don't forget to reset dummy head, if the inserted node becomes head node
      } else{
        curr=curr.next;// if no swap, update curr
      }
    }
    return dummy.next;
  }
}