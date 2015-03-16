package ch4treesandgraphs;

import java.lang.Integer;

/*
 * current.left.val <= current.val < current.right.val
 */
public class CheckBST {

  /*
   * in-order traversal: values of nodes should increase
   * this method has to assume no duplicate values in tree
   */
  // temp keeps tracking the value of last travasal node and compare it to current node
  static int temp = Integer.MIN_VALUE;
  static boolean isBST1(TreeNode node) {
    if (node == null) {
      return true;
    }
    if (isBST1(node.left) == false) { // in-order traversal, check left first, recurse to the left most node
      return false;
    }
    // check current node, assume no duplicated values
    // because if temp is current.left then temp and current can be equal
    // but if current is temp.right then current must strictly bigger than temp
    if (node.val <= temp) {
      return false;
    }
    temp = node.val;
    if (isBST1(node.right) == false) {
      return false;
    }
    return true;
  }

  /**
   * min-max solution, can handle duplicated values
   *
   * @param min Integer.MIN_VALUE
   * @param max Integer.MAX_VALUE
   */
  static boolean isBST2(TreeNode node, int min, int max) {
    if (node == null) {
      return true;
    }
    if (node.val <= min || node.val > max) {
      return false;
    }
    return isBST2(node.left, min, node.val) && isBST2(node.right, node.val, max);
  }
}