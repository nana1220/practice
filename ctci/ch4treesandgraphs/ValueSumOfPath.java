package ch4treesandgraphs;

import java.lang.System;
import java.util.LinkedList;

/*
 * Note: don't stop when reach the desired sum value because the node value can be zero
 *       or minus, the total sum can go down and back up to the target value
 */
public class ValueSumOfPath {

  /*
   * This method only prints all paths starting from root
   */
  static void printPaths1(TreeNode root, int sum, LinkedList<TreeNode> path) {
    if (root == null) {
      return;
    }
    path.add(root); // push current node to the path stack
    int pathSum = 0;
    for (TreeNode node : path) { // compute sum from root to current node
      pathSum += node.val;
    }
    if (pathSum == sum) {
      printPath(path);
    }
    printPaths1(root.left, sum, path);
    printPaths1(root.right, sum, path);
    path.pollLast(); // before return to the parent, pop current node out of path stack
  }

  /*
   * To print paths starting from any node, sum back from current node
   * to any of its ancestors and check if it's the desired sum.
   * Treat each current node as the end of the path, starting from the end
   * find all paths that have a desired sum.
   *
   * time: O(nlog(n) because at last level of the tree there n nodes to go through
   *       and since the depth is log(n) computing sums needs log(n) iterations for each node
   *       so it's nlog(n)
   * space: O(log(n)) because log(n) level needs to recurse log(n) times, and there is only
   *       one array for storing path whose length is also log(n)
   */
  static void printPaths2(TreeNode root, int sum, LinkedList<TreeNode> path) {
    if (root == null) {
      return;
    }
    path.add(root); // no need for a level parameter, path.size() == level
    int pathSum = 0;
    for (int i = path.size() - 1; i >= 0; i--) { // add from current node back up
      pathSum += path.get(i).val;
      if (pathSum == sum) {
        printPath(path, i);
      }
    }
    printPaths2(root.left, sum, path);
    printPaths2(root.right, sum, path);
    path.pollLast();
  }

  static void printPath(LinkedList<TreeNode> path) {
    for (TreeNode node : path) {
      System.out.print(node.val + " ");
    }
    System.out.println();
  }

  static void printPath(LinkedList<TreeNode> path, int startIndx) {
    for (int i = startIndx; i < path.size(); i++) {
      System.out.print(path.get(i).val + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(3);
    TreeNode node3 = new TreeNode(5);
    TreeNode node4 = new TreeNode(6);
    TreeNode node5 = new TreeNode(4);
    root.left = node1;
    root.right = node2;
    node1.left = node3;
    node1.right = node4;
    node2.left = node5;

    LinkedList<TreeNode> path = new LinkedList<TreeNode>();
    printPaths1(root, 8, path);
    System.out.println();
    path.clear();
    printPaths2(root, 8, path);
  }
}