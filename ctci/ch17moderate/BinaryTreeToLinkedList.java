/*
Consider a simple node-like data structure called BiNode, which has pointers to two
other nodes. The data structure BiNode could be used to represent both a binary
tree (where nodel is the left node and node2 is the right node) or a doubly linked
list (where nodel is the previous node and node2 is the next node). Implement a
method to convert a binary search tree (implemented with BiNode) into a doubly
linked list. The values should be kept in order and the operation should be performed
in place (that is, on the original data structure).
*/

package ch17moderate;


import java.lang.System;

public class BinaryTreeToLinkedList {

  static class BiNode {
    int val;
    BiNode left = null;
    BiNode right = null;

    public BiNode(int val) {
      this.val = val;
    }
    public String toString() {
      return val + " ";
    }
  }

  static class Pair {
    BiNode head = null;
    BiNode tail = null;
  }

  /*
   * On each node connect it's left subtree as head of list and right subtree as tail of list
   * return both head and tail of the linked list
   * tree left means list prev, tree right means list next
   * time: O(n)
   */
  static Pair convert1(BiNode root) {
    if (root == null) {
      return null;
    }
    Pair leftPair = convert1(root.left);
    Pair rightPair = convert1(root.right);
    Pair res = new Pair();
    if (leftPair != null) {
      leftPair.tail.right = root;
      root.left = leftPair.tail;
      res.head = leftPair.head;
    } else {
      res.head = root;
    }
    if (rightPair != null) {
      root.right = rightPair.head;
      rightPair.head.left = root;
      res.tail = rightPair.tail;
    } else {
      res.tail = root;
    }
    return res;
  }

  /*
   * return head of the linked list, but in each recursive call find the tail of left subtree
   * through its head
   * time: O(n^2)
   */
//  static BiNode convert2(BiNode root) {}

  /*
   * use circular linked list, return head of circular linked list, call head.left to get tail
   * time: O(n)
   */
  static BiNode convert3(BiNode root) {
    if (root == null) {
      return null;
    }
    BiNode leftHead = convert3(root.left);
    BiNode rightHead = convert3(root.right);
    // make the node itself a circular list with size 1
    root.left = root;
    root.right = root;
    if (leftHead == null && rightHead == null) {
      return root;
    }
    if (leftHead == null) {
      return circularConcat(root, rightHead);
    }
    if (rightHead == null) {
      return circularConcat(leftHead, root);
    }
    return circularConcat(circularConcat(leftHead, root), rightHead);
  }

  /*
   * Concat two circular linked lists, return head of first circular linked list as the head of result
   */
  static BiNode circularConcat(BiNode head1, BiNode head2) {
    if (head1 == null && head2 == null) return null;
    if (head1 == null) return head2;
    if (head2 == null) return head1;
    BiNode head1Tail = head1.left;
    BiNode head2Tail = head2.left;
    head1Tail.right = head2;
    head2.left = head1Tail;
    head1.left = head2Tail;
    head2Tail.right = head1;
    return head1;
  }
  /*
   * return head of non circular linked list
   */
  static BiNode convertToNonCircular(BiNode head) {
    head.left.right = null;
    head.left = null;
    return head;
  }

  public static void main(String[] args) {
    BiNode root = new BiNode(3);
    root.left = new BiNode(1);
    root.left.left = new BiNode(0);
    root.left.right = new BiNode(2);
    root.right = new BiNode(5);
    root.right.left = new BiNode(4);
    root.right.right = new BiNode(6);
    BiNode root1 = convert1(root).head;
    while (root1 != null) {
      System.out.print(root1);
      root1 = root1.right;
    }
    System.out.println();

    root = new BiNode(3);
    root.left = new BiNode(1);
    root.left.left = new BiNode(0);
    root.left.right = new BiNode(2);
    root.right = new BiNode(5);
    root.right.left = new BiNode(4);
    root.right.right = new BiNode(6);
    BiNode root3 = convertToNonCircular(convert3(root));
    while (root3 != null) {
      System.out.print(root3);
      root3 = root3.right;
    }
    System.out.println();
  }
}