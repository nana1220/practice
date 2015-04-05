/*
Given preorder and inorder traversal of a tree, construct the binary tree.

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

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  /*
          _______7______
       /              \
    __10__          ___2
   /      \        /
   4       3      _8
            \    /
             1  11
The preorder and inorder traversals for the binary tree above is:
preorder = {7,10,4,3,1,2,8,11}
inorder = {4,10,3,1,7,11,8,2}

The first node in preorder alwasy the root of the tree.
preorder:  {7}, {10,4,3,1}, {2,8,11}
inorder:     {4,10,3,1}, {7}, {11, 8,2}
   */

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length==0 || inorder.length==0)
      return null;
    return builder(preorder, 0, inorder, 0, inorder.length-1);
  }
  // time: O(n^2)
  // results from the fact that for every item in the Preorder traversal (of which there are n),
  // you have to search for its partition in the Inorder traversal, (again there are n of these).
  TreeNode builder(int[] preorder, int p, int[] inorder, int l, int r){
    if (p>=preorder.length || l>r)
      return null;
    int value = preorder[p]; // root
    int mid = getIndexOfValue(inorder, value, l, r); // mid position in inorder array
    TreeNode node = new TreeNode(value);
    node.left = builder(preorder, p+1, inorder, l, mid-1);
    // p+(mid-l)+1 is the position on the right of mid
    node.right = builder(preorder, p+(mid-l)+1, inorder, mid+1, r);
    return node;
  }

  int getIndexOfValue(int[] A, int target, int start, int end){
    for (int i=start; i<=end; i++){
      if (A[i]==target)
        return i;
    }
    return -1;
  }
}