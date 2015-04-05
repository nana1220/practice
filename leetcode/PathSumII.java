/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
 */

import java.lang.Integer;

/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class PathSumII {
  public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    ArrayList<Integer> path = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    pathSum(root, sum, paths, path);
    return paths;
  }

  void pathSum(TreeNode root, int sum, ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path) {
    if(root == null) return;
    path.add(root.val);
    sum = sum - root.val;
    if (root.left == null && root.right == null) {
      if (sum == 0)  paths.add(new ArrayList<Integer>(path));
      path.remove(path.size() - 1); // NOTE: remove before return
      return;
    }
    if (root.left != null) pathSum(root.left, sum, paths, path);
    if (root.right != null) pathSum(root.right, sum, paths, path);
    path.remove(path.size() - 1);
  }
}