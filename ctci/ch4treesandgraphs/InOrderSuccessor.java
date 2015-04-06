package ch4treesandgraphs;

/*
 * in-order traversal goes from left subtree to current node to right subtree
 * Case 1: the node has right subtree, its successor is the left most node of its right subtree
 * Case 2: the node has no right subtree, goes up the tree until get to a node which is
 *         another node's left child, then this 'another node' is the next not fully traversed
 *         node and also the successor. If there is no 'another node' even if get to the root,
 *         which means the node is the right most of BST, so it has no successor
 */
public class InOrderSuccessor {
  static TreeNode getSuccessor(TreeNode node) {
    if (node == null) {
      return null;
    }
    if (node.right != null) {
      TreeNode successor = node.right;
      while (successor.left != null) {
        successor = successor.left;
      }
      return successor;
    }
    // until child is the left child of child.parent or child.parent == root
    TreeNode child = node;
    TreeNode father = node.parent;
    while (father != null && father.left != child) {
      child = father;
      father = father.parent;
    }
    return father;
  }
}