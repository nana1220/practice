/*
 * return second largest value in bst
 */

public class SecondLargestNodeOfBst {

  /*
   * similar to leetcode BST iterator, use stack store right most path
   * if right most node has no left subtree, return its parent
   * otherwise return right most node of its left subtree
   */
  Node get2ndlarge(Node root) {
    // NOTE: edge case, if only one node in the BST, return null
    if (root == null || (root.left == null && root.right == null)) return null;
    Stack<Node> path = new Stack<Node>(); // NOTE: store path to the right most node
    while(root != null) {
      stack.push(node);
      node = node.next;
    }
    Node largest = stack.pop();
    if (largest.left == null) return stack.pop();
    Node tmp = largest.left;
    while (tmp.right != null) tmp = tmp.right;
    return tmp;
  }
}