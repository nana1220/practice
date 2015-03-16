package ch4treesandgraphs;

import java.lang.System;
import java.util.Arrays;

public class BstFromSortedArray {

  /*
   * use the middle element of sorted array as root of BST.
   * One trivial implementation is to fisrt build a BST of which the number of nodes equal to
   * array length and the value of root is the middle element of array, then for every other
   * elem in the array traverse tree find its node then insert the value.
   *
   * Recursively set root to the middle elem of array
   */
  static void createTree1(int[] sortedArray, TreeNode root) {
    int size = sortedArray.length;
    if (size == 1) {
      root.val = sortedArray[0];
      root.left = null;
      root.right = null;
      return;
    }
    if (size == 2) {
      root.val = sortedArray[1];
      root.left = new TreeNode();
      root.left.val = sortedArray[0];
      root.left.left = null;
      root.left.right = null;
      root.right = null;
      return;
    }
    int middle = size / 2;
    root.val = sortedArray[middle];
    root.left = new TreeNode();
    int[] leftSubArray = Arrays.copyOfRange(sortedArray, 0, middle); // middle index is exclusive
    createTree1(leftSubArray, root.left);
    root.right = new TreeNode();
    int[] rightSubArray = Arrays.copyOfRange(sortedArray, middle + 1, size);
    createTree1(rightSubArray, root.right);
  }

  /*
   * A better implementation.
   * Note: no need to copy array, just use first and last index indicating array's range
   * also return root node instead of return void
   */
  static TreeNode createTree2(int[] sortedArray, int first, int last) { // last: exclusive
    if (first >= last) {
      return null;
    }
    TreeNode root = new TreeNode();
    int mid = (first + last) / 2;
    root.val = sortedArray[mid];
    root.left = createTree2(sortedArray, first, mid);
    root.right = createTree2(sortedArray, mid + 1, last);
    return root;
  }

  public static void main(String[] args) {
    int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    TreeNode root = new TreeNode();
    createTree1(arr, root);
    root.printTree();
    System.out.println();
    root = createTree2(arr, 0, 9);
    root.printTree();
  }
}