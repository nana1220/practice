/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
  public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> res = new  ArrayList<ArrayList<Integer>> ();
    if(root==null){
      return res;
    }
    LinkedList<TreeNode> currlevel = new LinkedList<TreeNode>();
    currlevel.add(root); // level 1 is added here
    boolean oddlevel=false; // NOTE!!: because in the loop level 2 is first added,
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
      if(oddlevel) Collections.reverse(vals); // reverse result at odd level
      res.add(vals);
      oddlevel = !oddlevel;
      currlevel = nextlevel;

    }
    return res;
  }
}