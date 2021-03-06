/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 */

/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// recurrsion
public class Solution {
  public void flatten(TreeNode root) {
    if(root==null) return  ;
    build(root);
  }

  TreeNode build(TreeNode root) {
    if(root.left==null && root.right==null) return root;
    if(root.left==null) {
      root.right = build(root.right);
      return root;
    }
    if(root.right==null) {
      root.right = build(root.left);
      root.left=null;
      return root;
    }
    TreeNode l = build(root.left);
    TreeNode r = build(root.right);
    root.right = l;
    while(l.right!=null) l=l.right;
    l.right = r; // connect left and right
    root.left=null;
    return root;
  }
}

// Preorder traversal
// Use a prev pointer to connect
// time: O(n); space: recursive stack
public class Solution {
  TreeNode prev;
  public void flatten(TreeNode root) {
    if (root==null)
      return;
    if (prev!=null)
      prev.right = root;
    prev = root;
    TreeNode left = root.left, right = root.right;
    root.left = null; root.right=null;
    flatten(left);
    flatten(right);
  }
}

class solu{
  public void flatten(TreeNode root) {
    root = flatten(root, null);
  }

  TreeNode flatten(TreeNode cur, TreeNode next){
    if (cur == null) return next;
    next = flatten(cur.right, next);
    next = flatten(cur.left, next);
    cur.right = next;
    cur.left = null;
    return cur;
  }

  /*
   * Iterative version
   */
  public void flatten(TreeNode root){
    if (root == null) return;
    TreeNode prev = null, cur = root;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(cur);
    while (!stack.isEmpty()){
      cur = stack.pop();
      if (cur.right != null)
        stack.push(cur.right);
      if (cur.left != null)
        stack.push(cur.left);
      if (prev != null){
        prev.right = cur;
        prev.left = null;
      }
      prev = cur;
    }
  }
}