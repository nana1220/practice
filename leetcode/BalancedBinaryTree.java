/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
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
  public boolean isBalanced(TreeNode root) {
    return !(height(root)==-1);
  }

  int height(TreeNode root) { // return -1 if not balanced otherwise return height
    if(root==null) return 0;
    int leftHeight = height(root.left);
    if(leftHeight==-1) return -1;
    int rightHeight = height(root.right);
    if(rightHeight==-1) return -1;
    if (Math.abs(leftHeight - rightHeight) >1) return -1;
    return 1+Math.max(leftHeight, rightHeight);
  }
}