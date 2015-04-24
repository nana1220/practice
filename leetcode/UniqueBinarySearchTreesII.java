/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */

/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */

// split the numbers into 3 parts, [start, i-1], i, [i+1, end], which maps to node.left, node, node.right
// divide and conquer and merge
public class Solution {
  public List<TreeNode> generateTrees(int n) {
    List<TreeNode> res = generate(1,n);
    return res;
  }
  List<TreeNode> generate(int l, int r){
    List<TreeNode> roots = new ArrayList<TreeNode>();
    if(l > r) {
      roots.add(null);
      return roots;
    }
    for(int i=l; i<=r; i++){
      List<TreeNode> lnodes = generate(l,i-1);
      List<TreeNode> rnodes = generate(i+1, r);
      for(TreeNode lnode : lnodes){ // merge
        for(TreeNode rnode : rnodes){
          TreeNode root = new TreeNode(i);
          root.left=lnode;
          root.right=rnode;
          roots.add(root);
        }
      }
    }
    return roots;
  }

}