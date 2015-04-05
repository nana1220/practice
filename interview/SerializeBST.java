/*
given a BST, find a way to serilize all values in one string, then write a function to translate
the string back to the original tree
    n1
   /  \
 ab    h
 /   /  \
m   g2  cb
 */

// A Binary Search Tree (BST) is useful for storing phone book records in a memory limited device,
// such as a cell phone. The records are always maintained in sorted order, inserting and deleting
// a record takes O(lg n) time (slower than linked list, but much better than array).

// preorder traversal


import java.lang.String;
import java.util.*;

public class SerializeBST {
  void serialize(Node root, StringBuilder sb) {
    if (root == null) return;
    sb.append(root.name + "#"); //NOTE: only works if tree nodes do not contain "#"
    serialize(root.left, strs);
    serialize(root.right, strs);
  }

  String serialize(Node root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
  }

  /*
   * solu1: use BST insertion to deserialize, insertion log(n), n node total
   * so n(log(n))
   */
  void insert(Node root, String str) {
    if (arr[idx] <= root.name) {
      if (root.left != null) insert(root.left, str);
      else { root.left = new Node(str); return; }
    } else {
      if (root.right != null) insert(root.right, str);
      else {root.right = new Node(str); return; }
    }
  }

  Node deserlialize1(String strs) {
    String[] arr = strs.split("#");
    Node root = new Node(arr[0]);
    for (int i = 1; i < arr.length ; i++) {
      insert(root, arr[i]);
    }
    return root;
  }

  /*
   * solu2, min-max approach O(n) TODO: this is tricky, need pass reference
   * need to figure out a way, when finish left subtree trackback to right,
   * the value passed to right node updated to the lastest value, not the old one
   * that was passed to left node
   *
   * http://articles.leetcode.com/2010/09/saving-binary-search-tree-to-file.html
   * in comments there is a java version
   */
//  void deserialize2(Node node, String val, LinkedList<String> queue, int min, int max) {
//    if (val <= max && val > min) {
//      node.name = val;
//      String nextVal = queue.poll();
//      if (nextVal <= node.name && val > min) {
//        node.left = new Node(nextVal);
//        deserialize(node.left, arr, idx + 1, )
//      }
//    }
//  }
//
//  Node deserialize2(String str) {
//    String[] arr = str.split("#");
//    LinkedList<String> queue = new LinkedList<String>(Arrays.asList(arr));
//    String rootName = queue.poll();
//    Node root = new Node();
//    deserialize2(root, rootName, queue, Integer.MIN_VALUE, Intger.MAX_VALUE)
//  }
}

class Node {
  String name;
  Node left;
  Node right;
}







