package ch11sortingandsearching;

/*
Imagine you are reading in a stream of integers. Periodically, you wish to be able to
look up the rank of a number x (the number of values less than or equal to x). Imple-
ment the data structures and algorithms to support these operations. That is, imple-
ment the method track(int x), which is called when each number is generated,
and the methodgetRankOfNumber(int x), which returns the number of values
less than or equal to x.
 */

public class TrackingRank {
  Node root; // this is a BST

  void track(int x) {
    if (root == null) { // Note!!: first number comes, initialize root
      root = new Node(x);
    } else {
      root.insert(x);
    }
  }

  int getRankOfNumber(int x) {
    return root.getRank(x);
  }

  static class Node {
    int val;
    Node left;
    Node right;
    int leftCount; // count the number of nodes in left subtree

    Node(int x) {
      val = x;
      left = null;
      right = null;
      leftCount = 0;
    }

    /*
     * this is a modifiled version of BST insert
     */
    void insert(int x) {
      if (x <= val) {
        if (left == null) left = new Node(x); // Note!!: check null
        else left.insert(x);
        leftCount++; // leftCount count the number of nodes in current node’s left subtree
      } else {
        if (right == null) right = new Node(x);
        else right.insert(x);
      }
    }

    /*
     * this is a modified version of BST search
     */
    int getRank(int x) {
      if (x == val) return leftCount + 1; // plus 1 because need to include node itself
      if (x < val) {
        if (left == null) return -1; // Note!!: check null which means number not found
        else return left.getRank(x);
      } else {
        if (right == null) return -1;
          // Don’t forget to add right’s parent and all parent’s left subtree, so 1 + leftCount
          // right’s left subtree and right’s parent and right’s parent’s left subtree
          // are all numbers that are smaller than right
        else return leftCount + 1 + right.getRank(x);
      }
    }
  }
}