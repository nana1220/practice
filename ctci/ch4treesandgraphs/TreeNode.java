package ch4treesandgraphs;

import java.lang.System;
import java.util.LinkedList;

public class TreeNode {
  int val;
  TreeNode left = null;
  TreeNode right = null;
  TreeNode parent = null;

  void printTree() {
    LinkedList<TreeNode> queue = new LinkedList<ch4treesandgraphs.TreeNode>();
    System.out.println(this.val); // level 1, the root, depth 0
    queue.add(this);
    int level = 2; // depth 1
    int iterNum = 0;
    while (!queue.isEmpty()) {
      iterNum++;
      TreeNode node = queue.poll();
      if (node.left != null) {
        System.out.print(node.left.val + " ");
        queue.add(node.left);
      } else {
        System.out.print("  ");
      }
      if (node.right != null) {
        System.out.print(node.right.val + " ");
        queue.add(node.right);
      } else {
        System.out.print("  ");
      }
      // each iteration process two nodes, each level has 2 ^ (level - 1) nodes or 2 ^ depth
      // so 2 ^ (level - 1) / 2 iterations for each level
      if (iterNum == Math.pow(2, level - 2)) {
        System.out.print("\n");
        level++;
        iterNum = 0;
      }
    }
  }
}