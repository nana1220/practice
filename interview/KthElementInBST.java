public class KthElementInBST {

  /*
   * Find the kth smallest element (in order) in a binary search tree
   */

  // iterative in-order traversal, and use a variable to count the order
  public int findKthElementInBST(TreeNode root, int k) {
    if (root == null)
      return Integer.MIN_VALUE;
    Stack<TreeNode> st = new Stack<TreeNode>();
    TreeNode curr = root;
    while (curr != null) {
      st.push(curr);
      curr = curr.left;
    }
    int count = 0;
    while (!st.isEmpty()) {
      curr = st.pop();
      count++;
      if (count == k)
        return curr.val;
      curr = curr.right;
      while (curr != null) {
        st.push(curr);
        curr = curr.left;
      }
    }
    return Integer.MIN_VALUE;
  }
}
// Another solution is to use Order Statistics Tree, which takes O(lgn) to query the
// kth smallest element. See also CTCI/ch11_8_RankNumberInStream
