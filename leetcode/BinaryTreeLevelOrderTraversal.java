/*
    Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
    For example:
    Given binary tree {3,9,20,#,#,15,7},
        3
       / \
      9  20
        /  \
       15   7
    return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
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
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> res = new  ArrayList<ArrayList<Integer>> ();
    if(root==null){
      return res;
    }
    LinkedList<TreeNode> currlevel = new LinkedList<TreeNode>();
    currlevel.add(root);
    while(!currlevel.isEmpty()){
      LinkedList<TreeNode> nextlevel = new LinkedList<TreeNode>();
      ArrayList<Integer> vals = new ArrayList<Integer>();

      for(TreeNode n : currlevel){
        vals.add(n.val);
        if(n.left!=null)
          nextlevel.add(n.left);
        if(n.right!=null)
          nextlevel.add(n.right);
      }
      res.add(vals);
      currlevel = nextlevel;

    }
    return res;
  }
}

// DFS, when traverse the tree, put node into corresponding level's arraylist
public class Solution {
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    traversal(root, 0, res);
    return res;
  }

  // pass level information
  public void traversal(TreeNode root, int level, ArrayList<ArrayList<Integer>> res){
    if (root==null)
      return;
    if (level >= res.size()){ // first time entering this level
      ArrayList<Integer> r = new ArrayList<Integer>();
      r.add(root.val);
      res.add(r);
    }else
      res.get(level).add(root.val);
    traversal(root.left, level+1, res);
    traversal(root.right, level+1, res);
  }
}

