/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

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

// 第一遍循环，找出两个链表的长度差N
// 第二遍循环，长链表先走N步，然后同时移动，判断是否有相同节点
public class Solution {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA==null || headB==null) return null;
    ListNode a=headA, b= headB;
    int lenA=0;
    int lenB=0;
    while(a!=null){
      lenA++;
      a=a.next;
    }
    while(b!=null){
      lenB++;
      b=b.next;
    }
    a=headA; b= headB;
    if(lenA>lenB){
      int diff=lenA-lenB;
      while(diff>0){
        a=a.next;
        diff--;
      }
    } else{
      int diff=lenB-lenA;
      while(diff>0){
        b=b.next;
        diff--;
      }
    }
    while(a!=null){
      if(a==b) return a;
      a=a.next;
      b=b.next;
    }
    return null;
  }
}