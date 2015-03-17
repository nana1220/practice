package ch4treesandgraphs;

public class CheckSubtree {

  static boolean checkMatch(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) { // nothing left in both trees
      return true;
    } else if (root1 == null || root2 == null) { // Note: one tree may be deeper than another
      return false;
    }
    if (root1.val != root2.val) {
      return false;
    }
    return checkMatch(root1.left, root2.left) && checkMatch(root1.right, root2.right);
  }

  /*
   * search through large tree check if there is a subtree match
   * time: O(n + km) k is the number of times big tree has a node equal to small tree's root
   *       worst case O(nm) when every node equal to root of small tree
   * space: O(log(n) + log(m)) since there are log(n) recursive call for large tree traversal
   *        and log(m) recursive call for compare tree, memory usage is log(n) + log(m),
   *        n is node number of large tree, m is node number of small tree.
   *        More optimal in term of space which is important for scalability.
   */
  static boolean isSubtree(TreeNode root, TreeNode subtreeRoot) {
    if (subtreeRoot == null) { // Don't forget
      return true;
    }
    if (root == null) { // big tree empty, subtree not found
      return false;
    }
    if (root.val == subtreeRoot.val) {
//      return checkMatch(root, subtreeRoot)
//          || isSubtree(root.left, subtreeRoot) || isSubtree(root.right, subtreeRoot);
      if (checkMatch(root, subtreeRoot)) { // this cleaner than the above
        return true;
      }
    }
    return isSubtree(root.left, subtreeRoot) || isSubtree(root.right, subtreeRoot);
  }

  /*
   * Solution 2
   * create a string representing the in-order and pre-order traversals.
   * If both small tree's pre-order traversal and in-order traversal are substring
   * large tree's pre-order and in-order traversal, then it is a subtree.
   * Substrings can be checked with suffix trees in linear time.
   * Need to insert a special character into strings to indicate when a
   * left or right node is NULL.
   *
   * time: O(n+m), space: O(n+m)
   */
}