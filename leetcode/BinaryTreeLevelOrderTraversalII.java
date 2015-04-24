/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values.
(ie, from left to right, level by level from leaf to root).
For example:
Given binary tree {3,9,20,#,#,15,7},
        3
       / \
      9  20
        /  \
       15   7
eturn its bottom-up level order traversal as:
    [
      [15,7]
      [9,20],
      [3],
    ]
*/

// same as binaryTreeLevelOrderTraversal, plus reverse step
// time: O(n); space: O(b^d)
public class Solution {
  public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
    ArrayList<ArrayList<Integer>> res = new  ArrayList<ArrayList<Integer>> ();
    if(root==null){
      return res;
    }
    LinkedList<TreeNode> currlevel = new LinkedList<TreeNode>();
    currlevel.add(root);
    while(!currlevel.isEmpty()){
      LinkedList<TreeNode> nextlevel = new LinkedList<TreeNode>();
      ArrayList<Integer> vals = new ArrayList<Integer>();

      for(TreeNode n : currlevel){
        vals.add(n.val);
        if(n.left!=null)
          nextlevel.add(n.left);
        if(n.right!=null)
          nextlevel.add(n.right);
      }
      res.add(vals);
      currlevel = nextlevel;

    }
    Collections.reverse(res); // NOTE: simply use Collections.reverse
    return res;
  }
}
