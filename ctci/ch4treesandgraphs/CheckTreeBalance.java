package ch4treesandgraphs;

import java.lang.System;
import java.util.LinkedList;

public class CheckTreeBalance {

  public static void main(String[] args) {
    TreeNode root = new TreeNode();
    TreeNode left1 = new TreeNode();
    TreeNode left2 = new TreeNode();
    TreeNode right1 = new TreeNode();
    TreeNode right2 = new TreeNode();
    root.left = left1;
    root.right = right1;
    left1.left = left2;
    right1.right = right2;
    System.out.println(checkTreeBalance(root));
    System.out.println(checkTreeBalanceRecur(root));
    System.out.println(isBalance(root));

    root.left = left1;
    root.right = right1;
    left1.left = left2;
    right1.right = null;
    left2.right = right2;
    System.out.println(checkTreeBalance(root));
    System.out.println(checkTreeBalanceRecur(root));
    System.out.println(isBalance(root));
  }

  /*
   * time: O(n), space O(H), H is tree height
   */
  static int height(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(height(node.left), height(node.right));
  }

  /*
   * BFS traverse, iterative approach, time: O(n^2)
   */
  static boolean checkTreeBalance(TreeNode root) {
    if (root == null) { // don't forget
      return true;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    if (Math.abs(height(root.left) - height(root.right)) > 1) {
      return false;
    }
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node.left != null) { // don't forget to check if child is not null
        TreeNode leftNode = node.left;
        if (Math.abs(height(leftNode.left) - height(leftNode.right)) > 1) {
          return false;
        }
        queue.add(leftNode);
      }
      if (node.right != null) { // don't forget to check if child is not null
        TreeNode rightNode = node.right;
        if (Math.abs(height(rightNode.left) - height(rightNode.right)) > 1) {
          return false;
        }
        queue.add(rightNode);
      }
    }
    return true;
  }

  /*
   * recursive approach, time O(n^2)
   */
  static boolean checkTreeBalanceRecur(TreeNode root) {
    if (root == null) { // base case
      return true;
    }
    if (Math.abs(height(root.left) - height(root.right)) > 1) {
      return false;
    }
    return checkTreeBalanceRecur(root.left) && checkTreeBalanceRecur(root.right);
  }

  /*
   * check balance while compute height, if non-balance return -1 otherwise return height
   * time: O(n), space O(H), H is tree height
   */
  static int checkHeight(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int leftHeight = checkHeight(node.left); // check if left is balanced
    if (leftHeight == -1) {
      return -1;
    }
    int rightHeight = checkHeight(node.right); // check if right is balanced
    if (rightHeight == -1) {
      return -1;
    }
    if (Math.abs(leftHeight - rightHeight) > 1) { // check if current is balanced
      return -1;
    }
    return 1 + Math.max(height(node.left), height(node.right));
  }

  static boolean isBalance(TreeNode root) {
    // now only compute the root height will give the answer for balance of tree
    return checkHeight(root) != -1 ? true : false;
  }
}

