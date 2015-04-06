package ch4treesandgraphs;

/*
 * find lowest common ancestor (LCA)
 * case 1: if it's a BST, use find method for finding two nodes and locate the node where the path diverge
 * case 2: if not BST, but nodes have link to their parent, can trace and store their paths to the root
 *         the first time paths intersect is where LCA is
 * case 3: not BST, no links to parent node
 */

/*
 * implement case 3
 * log(n)
 */
public class FindCommonAncestor {
  // top-down approach
  // isAncestor means descendant is on the subtree of ancestor
  static boolean isAncestor(TreeNode ancestor, TreeNode descendant) {
    if (ancestor == null || descendant == null) {
      return false;
    }
    if (ancestor == descendant) {
      return true;
    }
    return isAncestor(ancestor.left, descendant) || isAncestor(ancestor.right, descendant);
  }

  /*
   * call isAncestor as getLCA1, top-down approach
   * time: O(n) if tree is balanced (2^(log(n)) = n), worst case O(n^2)
   *
   * This algorithm runs in O(n) time on a balanced tree.This is because isAncestor is called on
2n nodes in the first call (n nodes for the left side, and n nodes for the right side). After
that, the algorithm branches left or right, at which point isAncestor will be called on 2n/2
nodes, then 2n/4, and so on.This results in a runtime of 0(n).

   */
  static TreeNode getLCA0(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null) {
      return null;
    }
    if (!isAncestor(root, node1) || !isAncestor(root, node2)) { // check if one node is not in tree
      return null;
    }
    boolean isNode1OnLeft = isAncestor(root.left, node1);
    boolean isNode2OnLeft = isAncestor(root.left, node2);
    if (isNode1OnLeft != isNode2OnLeft) { // if on different subtree, root is LCA
      return root;
    }
    // on same subtree, getLCA0 only recurse on the root which is a common ancestor
    if (isNode1OnLeft) { // left subtree
      return getLCA0(root.left, node1, node2);
    } else { // right subtree
      return getLCA0(root.right, node1, node2);
    }
  }

  /*
   * call isAncestor but traverse  bottom-up
   * only search the entire tree once to find p and
q. We should then be able to "bubble up" the findings to earlier nodes in the stac
   */
  static TreeNode getLCA1(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null || node1 == null || node2 == null) {
      return null;
    }
    if (!isAncestor(root, node1) || !isAncestor(root, node2)) { // base case
      // root is not common ancestor, e.g. root == node1 but node1 and node2 not on the same subtree
      return null;
    }
    // root is a common ancestor but might not be the lowest one
    // left and right are roots for two different subtrees, node1 and node2 can either
    // both on one of the subtree or on different subtree
    // it's impossibe for node1 or node2 on two subtree at the same time
    // both recursive calls will return when hitting the base case
    // The worst case is when node1 and node2 are left and right children of a common parent
    // and call recurse to this parent, then leftCA and rightCA are both null.
    TreeNode leftCA = getLCA1(root.left, node1, node2);
    TreeNode rightCA = getLCA1(root.right, node1, node2);
    if (leftCA == null && rightCA == null) { // on different subtree
      return root;
    } else if (leftCA == null) { // both on right subtree
      return rightCA;
    } else { // both on left subtree
      return leftCA;
    }
  }

  // bottom-up approach: bubble up the findings to earlier nodes in the stack
  // Improve over the top-down approach by avoiding traversing
  // the same nodes over and over for ancestor check.
  // Once reach node1 or node2, pass it up to its parent, until both nodes
  // are bubbled up to the same parent which is their LCA, and this parent
  // will be bubbled up to root and return.
  static TreeNode getLCA2(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null) { // base case: find neither of two nodes
      return null;
    }
    // Note: This condition is the only difference between top-down and bottom-up
    // and this condition introduce a bug into bottom-up
    // it cannot handle the case when one node is not in the tree
    // can first search tree to make sure both nodes appear
    if (root == node1 || root == node2) {
      // base case: once find the node return it, return node if subtree include node
      return root;
    }
    TreeNode left = getLCA2(root.left, node1, node2); // traverse from the bottom
    TreeNode right = getLCA2(root.right, node1, node2); // traverse from the bottom
    // case 1: if node1 and node2 are returned from different subtree return root as LCA
    if (left != null && right != null) {
      return root;
    }
    // case 2: if only one node is returned from a subtree, return this node, another subtree includes nothing
    //         if both nodes are not found return null
    return left != null ? left : right;
    // case 3: it's not possible two nodes in the same subtree, since recurse goes buttom up
    //         when two nodes merge into one tree, the root for that tree is instead returned
    //         and bubble up since other branch will always be null
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(4);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(6);
    TreeNode node3 = new TreeNode(1);
    TreeNode node4 = new TreeNode(3);
    TreeNode node5 = new TreeNode(5);
    root.left = node1;
    root.right = node2;
    node1.left = node3;
    node1.right = node4;
    node2.left = node5;

    System.out.println(getLCA0(root, node3, node4).val == node1.val);
    System.out.println(getLCA0(root, node1, node5).val == root.val);
    System.out.println(getLCA1(root, node3, node4).val == node1.val);
    System.out.println(getLCA1(root, node1, node5).val == root.val);
    System.out.println(getLCA2(root, node3, node4).val == node1.val);
    System.out.println(getLCA2(root, node1, node5).val == root.val);
  }
}