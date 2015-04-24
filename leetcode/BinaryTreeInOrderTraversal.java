/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

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
public class Solution {
  // iterative
  public List<Integer> inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    List<Integer> res = new ArrayList<Integer>();
    while(root!=null){
      stack.push(root);
      root=root.left;
    }
    while(!stack.isEmpty()){
      TreeNode n = stack.pop();
      res.add(n.val);
      TreeNode r = n.right;
      while(r!=null){
        stack.push(r);
        r=r.left;
      }
    }
    return res;
  }
  void dfs(TreeNode root, List<Integer> res){
    if(root==null) return;
    dfs(root.left, res);
    res.add(root.val);
    dfs(root.right,res);
  }
}