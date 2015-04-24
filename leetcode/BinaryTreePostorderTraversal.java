/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
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

// iterative
/*
两种解法。iterative的时候，用stack来存parent。比较tricky的一点是left child遍历完以后，如果right child非空，则需要跳到right child，
而不需要把parent给pop出来。当right child也处理完之后，才是把parent给pop出来。因此需要添加一个变量pre，用来记录刚好之前访问的node。
所以每次对stack进行操作的时候，都是先peek一下，然后使用条件：
peekNode.right!=null && pre!=peekNode.right
而每次visit节点之后（也即res.add(node)），需要更新pre
 */
public class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> st = new Stack<TreeNode>();
    TreeNode pre =null;
    while (root!=null || !st.empty()) {
      if (root!=null) {
        st.push(root);
        root = root.left;
      }
      else {
        TreeNode peekNode = st.peek();
        // if peekNode has no right child then traverse goes from left child to peekNode, nothing special
        // peek node has right child, then traverse goes from right child to peekNode,
        // so must use preNode to record last processed node, otherwise right child will be processed again and infinite loop
        if (peekNode.right!=null && peekNode.right!=pre) {
          root = peekNode.right;
        }
        else {
          st.pop();
          res.add(peekNode.val);
          pre = peekNode;
        }
      }
    }
    return res;
  }
}



// recursive
public class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    postorder(res, root);
    return res;
  }
  void postorder(List<Integer> res, TreeNode root){
    if(root==null) return;
    postorder(res,root.left);
    postorder(res,root.right);
    res.add(root.val);
  }
}