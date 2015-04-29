/*
找BST中第k小的节点。
 */

// inorder traversal
// 也可以用stack来做iterative的中序遍历，直接count一下就好，也是O(N)。
class Solu {
  int get_kth(TreeNode*root, int k) {
    int ret = 0, count = 0;

    function<void (TreeNode *) > solve =[&](TreeNode * node) {
      if (!node) return;
      solve(node -> left);
      if (++count == k) {
        ret = node -> val;
        return;
      }
      solve(node -> right);
    }
    ;
    solve(root);
    return ret;
  }
}

/*
如果想更优化效率，可以引入order statistic tree。
Order statistic tree是一种BST的变种，往每个node里面加上一个field来存左子树的节点数，在树平衡时可以达到O(logN)复杂度。
 */