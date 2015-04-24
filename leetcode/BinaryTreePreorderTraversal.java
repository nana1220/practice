/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
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

// iterative
public class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while(root!=null){
      while(root !=null){
        res.add(root.val);
        if(root.right!=null){
          stack.push(root.right);
        }
        root=root.left;
      }
      if(!stack.isEmpty()) root=stack.pop();
    }
    return res;
  }
}


// recursive
public class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    preorder(res, root);
    return res;
  }
  void preorder(List<Integer> res, TreeNode root){
    if(root==null) return;
    res.add(root.val);
    preorder(res, root.left);
    preorder(res, root.right);
  }
}