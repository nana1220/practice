/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 */

/*
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

// correct recursion
// time O(n) space O(log(n))
public class Solution {
  public void connect(TreeLinkNode root) {
    if (root == null || root.left==null && root.right==null)   return;
    root.left.next = root.right;
    if (root.next != null)  root.right.next = root.next.left; //NOTE!!!, how to connect nodes with different parent
    connect(root.left);
    connect(root.right);
  }
}

// iterative
public class Solution {
  public void connect(TreeLinkNode root) {
    if(root==null) return;
    while(root.left!=null){ // depth traversal
      TreeLinkNode n = root;
      while(n!=null){ // level traversal
        n.left.next = n.right;
        if(n.next!=null) {
          n.right.next = n.next.left;
        }
        n=n.next;
      }
      root=root.left;
    }
  }
}

// wrong recursion: TLE
public class Solution {
  public void connect(TreeLinkNode root) {
    if(root==null) return;
    root.next=null;
    recurse(root.left, root.right);
  }
  void recurse(TreeLinkNode l, TreeLinkNode r){
    if(l==null && r==null) return;
    l.next = r;
    r.next =null;
    recurse(l.left, l.right);
    recurse(l.right, r.left);
    recurse(r.left, r.right);
  }
}
