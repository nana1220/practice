/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */




import java.util.*;

public class PathSum {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root==null)  return false; // remember alwasy check null input
    ArrayList<Integer> sums = new ArrayList<Integer>();
    pathSum(root, 0, sums);
    return sums.contains(sum);
  }

  void pathSum(TreeNode root, int sum, ArrayList<Integer> sums) {
    if (root.left == null && root.right == null)  {sums.add(sum + root.val); return;}
    if (root.left != null) pathSum(root.left, sum + root.val, sums);
    if (root.right != null) pathSum(root.right, sum + root.val, sums);
  }

  /*
   * A very simple solution
   */
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root==null)  return false;
    sum -= root.val;
    if (root.left==null && root.right==null) return sum==0;
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }

  /* Definition for binary tree */
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }


}