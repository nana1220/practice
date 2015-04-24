/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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


//    1
//   / \
//  2   *   minDepth is 2, null node doesn't count
public class Solution {
  public int minDepth(TreeNode root) {
    if(root==null) return 0; // only handle null input
    // base cases, should end at a node not a null
    if(root.left==null && root.right ==null) return 1;
    if(root.left==null) return 1+ minDepth(root.right);
    if(root.right==null) return 1+ minDepth(root.left);
    return 1+ Math.min(minDepth(root.left), minDepth(root.right));
  }
}