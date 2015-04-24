/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
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
  public int sumNumbers(TreeNode root) {
    if(root==null) return 0;
    int[] sum = new int[1];
    ArrayList<Integer> path = new ArrayList<Integer>();
    dfs(root, path, sum);
    return sum[0];
  }
  void dfs(TreeNode root, ArrayList<Integer> path, int[] sum) {
    // NOTE:!!! use leaf as termination condition,
    // don't use root==null as termination condition, otherwise, a leaf will call its left and right, and add compute its path sum twice
    // if(root==null){
    if(root.left==null && root.right==null){
      path.add(root.val);
      int base = 1;
      int num=0;
      for(int i=path.size()-1; i>=0; i--){
        num += base * path.get(i);
        base *=10;
      }
      sum[0] += num;
      path.remove(path.size()-1);
      return;
    }
    path.add(root.val);
    if(root.left!=null){
      dfs(root.left, path, sum);
    }
    if(root.right!=null){
      dfs(root.right, path, sum);
    }
    path.remove(path.size()-1);
  }
}