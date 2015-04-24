/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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

// NOTE!!!, swap the first and last node in the wrong order list
// NOTE:!!!!!!!! only need to swap value of nodes, no need to swap nodes
// E.g. Given [1, 4, 3, 2, 5], which two shall we swap to fix the array?
// One way is starting from the second element in the list and comparing it with its
// previous one. If it is smaller than its previous one, then we know at least one of
// the two is in wrong spot. So, the larger one in the first pair and the smaller one
// in the second pair are the ones we are looking for.
// But, how about [1, 3, 2, 4, 5]?
// This tells us that if there is only one such pair where the latter one is greater
// than the previous one. That pair is actually the two element we are looking for.
// use stack O(n) space
public class Solution {
  public void recoverTree(TreeNode root) {
    if(root ==null) return;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    ArrayList<TreeNode> swap = new ArrayList<TreeNode>();
    while(root!=null){
      stack.push(root);
      root=root.left;
    }
    TreeNode prev=null;
    while(!stack.isEmpty()){
      TreeNode curr= stack.pop();
      if(prev!=null && curr.val <= prev.val){
        swap.add(prev);
        swap.add(curr);
      }
      TreeNode right=curr.right;
      while(right!=null){
        stack.push(right);
        right=right.left;
      }
      prev=curr;
    }
    int tmp = swap.get(0).val;
    swap.get(0).val =  swap.get(swap.size()-1).val;
    swap.get(swap.size()-1).val = tmp;
  }
}

// Morris Traversal in order traversal, threaded tree
// Maintain a prev pointer
// Write the morris traversal first, then add code to find swapped nodes
// time: O(n); space: O(1)
/*
1. Initialize current as root
2. While current is not NULL
   If current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Make current as right child of the rightmost node in current's left subtree
      b) Go to this left child, i.e., current = current->left
 */
public class Solution {
  public void recoverTree(TreeNode root) {
    if (root==null) return;
    TreeNode curr = root, prev = null; // maintain two pointers, curr node and prev node
    TreeNode n1=null, n2=null; // fisrt out of order node and last out of order node
    while (curr!=null){
      if (curr.left==null){
        if (prev!=null && prev.val > curr.val){
          n2 = curr;
          if (n1==null)   n1 = prev;
        }
        prev = curr;
        curr = curr.right;
      }else{
        TreeNode p = curr.left; // find current node's in order predecessor!!!!!
        while (p.right != null && p.right != curr) // don't forget second condition
          p = p.right;
        if (p.right == null) { // Set link: make current as right child of the rightmost node in current's left subtree
          p.right = curr;
          curr = curr.left;
        } else {
          p.right = null; // unset link
          if (prev != null && prev.val > curr.val) {
            n2 = curr;
            if (n1 == null)
              n1 = prev;
          }
          prev = curr;
          curr = curr.right;
        }
      }
    }
    swap(n1, n2);
  }
  private void swap(TreeNode n1, TreeNode n2){
    int tmp = n1.val;
    n1.val = n2.val;
    n2.val = tmp;
  }
}