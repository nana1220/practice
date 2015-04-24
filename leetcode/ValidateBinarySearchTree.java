/*
    Given a binary tree, determine if it is a valid binary search tree (BST).
    Assume a BST is defined as follows:
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
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

// in order traversal
public class Solution {
  public boolean isValidBST(TreeNode root) {
    return inOrder(root);
  }
  TreeNode prev = null; //NOTE!!!!!!!, store previous node, check with null for the first node,  don't store value
  boolean inOrder(TreeNode root){
    if(root==null) return true;
    if(!inOrder(root.left)) return false;
    // check with null
    if (prev !=null && root.val <= prev.val) return false;
    prev = root;
    if(!inOrder(root.right)) return false;
    return true;
  }
}

// min-max checker, cannot pass big integer that renders overflow
public class Solution {
  public boolean isValidBST(TreeNode root) {
    return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  boolean isValid(TreeNode root, int min, int max){
    if(root ==null ) return true;
    if(root.val <= min || root.val >= max) return false;
    if (!isValid(root.left, min, root.val)) return false;
    if (!isValid(root.right, root.val, max)) return false;
    return true;
  }
}