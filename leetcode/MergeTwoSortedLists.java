/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing
together the nodes of the first two lists.
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
public class MergeTwoSortedLists {
  /*
   * maintain head and current node
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    if (l2 == null) return l1;
    if (l1 == null) return l2;
    ListNode head = null;
    // NOTE: make a dummpy node instead of make it null, thus can call curr.next
    ListNode curr = new ListNode(0);
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        if (head == null) {
          head = l1;
        }
        curr.next = l1;
        l1 = l1.next;
      } else {
        if (head == null) {
          head = l2;
        }
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }
    if (l1 == null) l1 = l2;
    if (l1 != null) curr.next = l1;
    return head;
  }
}