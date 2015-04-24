/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
 */

// recursion
public class Solution {
  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    TreeLinkNode p = root.next;
    while (p != null) { // find next node's first non-null child, otherwise p=null
      if (p.left != null) {
        p = p.left;
        break;
      }
      if (p.right != null) {
        p = p.right;
        break;
      }
      p = p.next;
    }
    // connect current node's children and next node's first non null child
    if (root.right != null) {
      root.right.next = p;
    }
    if (root.left != null) {
      root.left.next = root.right == null ? p : root.right;
    }
    // recursion
    connect(root.right);
    connect(root.left);
  }
}

// iterative O(1) space
public class Solution {
  public static void connect(TreeLinkNode root) {
    while (root != null) {
      TreeLinkNode next = null;  // the first node of next level
      TreeLinkNode prev = null;   // previous node on the same level
      while (root!=null){
        if (next == null)   next = root.left!=null ? root.left : root.right;
        if (root.left != null) {
          if (prev != null)    prev.next = root.left;
          prev = root.left;
        }
        if (root.right != null) {
          if (prev != null)    prev.next = root.right;
          prev = root.right;
        }
        root = root.next;
      }
      root = next;
    }
  }
}