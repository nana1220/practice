/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
  public int maxDepth(TreeNode root) {
    depth(root, 0);
    return max;
  }
  int max=0; // can pass an one dim array instead use global value
  void depth(TreeNode root, int dep){ // void depth(TreeNode root, int dep, int[] max)
    if(root==null){
      max=Math.max(dep, max);
      return;
    }
    depth(root.left, dep+1);
    depth(root.right, dep+1);
  }
}

// recursive
// time: O(n)
public class Solution {
  public int maxDepth(TreeNode root) {
    if (root == null)   return 0;
    return 1+ Math.max(maxDepth(root.left), maxDepth(root.right));
  }
}