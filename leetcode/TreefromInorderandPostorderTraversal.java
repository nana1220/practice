/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
// root is the last element in postorder, then still find the root in inorder and split left subtree and right subtree
public class Solution {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder.length==0) return null;
    int rootVal = postorder[postorder.length-1];
    int i=0;
    while(inorder[i] != rootVal) i++;
    int leftSize=i;
    int[] inorderL = Arrays.copyOfRange(inorder,0, leftSize);
    int[] inorderR = Arrays.copyOfRange(inorder, leftSize+1, inorder.length);
    int[] postorderL = Arrays.copyOfRange(postorder, 0, leftSize);
    int[] postorderR = Arrays.copyOfRange(postorder, leftSize, postorder.length-1);
    TreeNode root=new TreeNode(rootVal);
    root.left=buildTree(inorderL, postorderL);
    root.right=buildTree(inorderR, postorderR);
    return root;
  }
}
