/*
Given a Binary Tree, find the deepest leaf node that is right child of its parent.
For example:
     _______1______
    /              \
 ___2__             3
/      \
4       5
         \
          6
should return 6.
 */

// pass level parameter to dfs, if level is current max level, store its right child
class Solu{
  int deepest_left_node(TreeNode* root) {
    int max_level = 0, ret = 0;

    function<void(TreeNode*, int)> dfs = [&](TreeNode* r, int level) {
      if(!r) return;

      if(level > max_level && r.right!= null) {
        max_level = level;
        ret = r->val;
      }
      dfs(r->left, level + 1);
      dfs(r->right, level + 1);
    };
    dfs(root, 1);
    return ret;
  }
}