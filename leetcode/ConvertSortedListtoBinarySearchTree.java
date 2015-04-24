/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  public TreeNode sortedListToBST(ListNode head) {
    if(head==null) return null;
    if(head.next==null) return new TreeNode(head.val);
    ListNode leftHead = head;
    ListNode dummy = new ListNode(0);
    dummy.next = head; // dont forget
    ListNode slow = dummy;
    ListNode fast = head; //if fast = dummy, slow becomes mid, not slow.next, carefull!! check odd and even case to see if the mid is
    while(fast !=null && fast.next !=null){
      slow=slow.next;
      fast=fast.next.next;
    }
    ListNode mid = slow.next;
    ListNode rightHead = mid.next;
    mid.next=null; // not set null is not a problem
    slow.next =null; // Remeber to cut left list here
    TreeNode root = new TreeNode(mid.val);
    root.left = sortedListToBST(leftHead);
    root.right = sortedListToBST(rightHead);
    return root;
  }
}