/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

// binary search the array to build tree
public class Solution {
  public TreeNode sortedArrayToBST(int[] num) {
    if(num.length==0) return null;
    int mid = num.length/2;
    TreeNode root = new TreeNode(num[mid]);
    int[] numL = Arrays.copyOfRange(num,0,mid);
    int[] numR = Arrays.copyOfRange(num,mid+1,num.length);
    root.left=sortedArrayToBST(numL);
    root.right=sortedArrayToBST(numR);
    return root;
  }
}
// or use index, no copy
public class Solution {
  public TreeNode sortedArrayToBST(int[] num) {
    if (num == null || num.length == 0) return null;
    return builder(num, 0, num.length-1);
  }

  public TreeNode builder(int[] num, int start, int end){
    if (start > end)    return null; //NOTE: recursion use if(l>r), iteration use while(l<=r)
    int mid = (start + end) >> 1;
    TreeNode root = new TreeNode(num[mid]);
    root.left = builder(num, start, mid-1);
    root.right = builder(num, mid+1, end);
    return root;
  }
}