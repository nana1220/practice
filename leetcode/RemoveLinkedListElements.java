/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
 */

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next =head;// DONT forget to connect to head!!!!!!!!!!
    ListNode dummyhead = dummy;
    while(dummy.next !=null){
      if(dummy.next.val == val){
        dummy.next = dummy.next.next; // remove node don't update dummy
      }
      else dummy=dummy.next; // if no removal update dummy
    }
    return dummyhead.next;
  }
}