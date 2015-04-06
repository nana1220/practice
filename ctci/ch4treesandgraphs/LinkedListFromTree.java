package ch4treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * create linked list from binary tree, each level for one linked list
 */
public class LinkedListFromTree {

  /*
   * DFS recursive: lists is passed by reference since it is shared by all recursive calls
   * pass level info which indicates which level the passed node belongs to
   * time: O(N);
   * space: O(N) because need to return O(N) data,
   *        O(logN) recursive calls since tree height is O(logN) (N is nodes number)
   *        overall O(N)
   */
  static void createListsDFS1(TreeNode root, int level, ArrayList<LinkedList<TreeNode>> lists) {
    if (root == null) {
      return;
    }
    LinkedList<TreeNode> list;
    if (level > lists.size()) { // if first time get to this level (level is 1-based)
      list = new LinkedList<TreeNode>();
      lists.add(list); // levels are always traversed in order, so they are added in order
    } else {
      list = lists.get(level - 1);
    }
    list.add(root);
    createListsDFS1(root.left, level + 1, lists);
    createListsDFS1(root.right, level + 1, lists);
  }

  /*
   * DFS iterative
   */
  static void createListsDFS2(TreeNode root, int level, ArrayList<LinkedList<TreeNode>> lists) {

  }

  /*
   * BFS, check if poped node belongs to previous level, if ture put its children to current level,
   * if false it belongs to current level its children belongs to next level
   */
  static ArrayList<LinkedList<TreeNode>> createListsBFS1(TreeNode root) {
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

    ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
    LinkedList<TreeNode> currLevel = new LinkedList<TreeNode>();
    lists.add(currLevel);
    currLevel.add(root);
    if (root.left == null && root.right == null) {
      return lists;
    }
    queue.add(root);
    LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (!currLevel.contains(node)) { // means nextLevel is not empty and its element is dequeued
        lists.add(nextLevel); // add non-empty nextLevel
        currLevel = nextLevel;
        nextLevel = new LinkedList<TreeNode>();
      }
      if (node.left != null) {
        nextLevel.add(node.left);
        queue.add(node.left);
      }
      if (node.right != null) {
        nextLevel.add(node.right);
        queue.add(node.right);
      }
    }
    return lists;
  }

  /*
   * BFS, a better impl. no need to use additional queue
   * time O(N), space O(N) ( O(N) for data O(log(N)) for recursive stack, so O(N) )
   */
  static ArrayList<LinkedList<TreeNode>> createListsBFS2(TreeNode root) {
    if (root == null) {
      return null;
    }
    ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
    LinkedList<TreeNode> currLevel = new LinkedList<TreeNode>();
    currLevel.add(root);
    // Note: currLevel changes after each iteration, same effect as a regular queue for BFS
    while (!currLevel.isEmpty()) {
      lists.add(currLevel);
      LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();
      for (TreeNode node : currLevel) {
        if (node.left != null) {
          nextLevel.add(node.left);
        }
        if (node.right != null) {
          nextLevel.add(node.right);
        }
      }
      currLevel = nextLevel;
    }
    return lists;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode();
    root.val = 1;
    root.left = new TreeNode();
    root.left.val =2;
    root.right = new TreeNode();
    root.right.val = 3;
    root.left.left = new TreeNode();
    root.left.left.val = 4;
    root.left.right = new TreeNode();
    root.left.right.val = 5;
    root.right.left = new TreeNode();
    root.right.left.val = 6;
    root.right.right = new TreeNode();
    root.right.right.val = 7;

    ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
    createListsDFS1(root, 1, lists);
    for (LinkedList<TreeNode> list : lists) {
      for (TreeNode node : list) {
        System.out.print(node.val + " ");
      }
      System.out.println();
    }

    lists = createListsBFS1(root);
    for (LinkedList<TreeNode> list : lists) {
      for (TreeNode node : list) {
        System.out.print(node.val + " ");
      }
      System.out.println();
    }

    lists = createListsBFS2(root);
    for (LinkedList<TreeNode> list : lists) {
      for (TreeNode node : list) {
        System.out.print(node.val + " ");
      }
      System.out.println();
    }
  }
}