/*
You are given two linked lists representing two non-negative numbers. The digits are stored in
reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */

/*
 * Definition for singly-linked list.
 */
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    ListNode head = new ListNode(0);
    ListNode curr = null;
    int carry = 0;
    while (l1 != null && l2 != null) {
      if (curr == null) {
        curr = head;
      } else {
        curr.next = new ListNode(0);
        curr = curr.next;
      }
      int sum = l1.val + l2.val + carry;
      if (sum > 9) carry = 1;
      else carry = 0;
      sum %= 10;
      curr.val = sum;
      l1 = l1.next;
      l2 = l2.next;
    }
    if (l2 != null) l1 = l2;
    while (l1 != null) {
      int sum = l1.val + carry;
      if (sum > 9) carry = 1;
      else carry = 0;
      sum %= 10;
      curr.next = new ListNode(sum);
      curr = curr.next;
      l1 = l1.next;
    }
    if (carry == 1) {
      curr.next = new ListNode(1);
    }
    return head;
  }
}