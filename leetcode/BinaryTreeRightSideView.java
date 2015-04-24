/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
 */


// level order traversal, take right most node on each level
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
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if(root==null) return res;
    LinkedList<TreeNode> currentlevel = new LinkedList<TreeNode>();
    currentlevel.add(root);
    while(!currentlevel.isEmpty()){
      res.add(currentlevel.peek().val);
      LinkedList<TreeNode> nextlevel = new LinkedList<TreeNode>();
      for(TreeNode n : currentlevel){
        if(n.right != null){ // for right view, add right child first
          nextlevel.add(n.right);
        }
        if(n.left != null){
          nextlevel.add(n.left);
        }
      }
      currentlevel=nextlevel;
    }
    return res;
  }
}
