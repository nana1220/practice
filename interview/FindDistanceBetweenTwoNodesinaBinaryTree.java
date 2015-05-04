/*
Find the distance between two keys in a binary tree, no parent pointers are given.


这题可以先求出两个节点的LCA，然后分别计算离LCA的深度，加起来就可以了。当然纯粹这样做有一些重复运算
我们可以在找LCA的时候就把两个节点分别离根节点的距离算出来，如果两个节点是上下级关系，就需要把下边的那个节点的高度单独再算一次。
 */

class Solu{
  int get_distance(TreeNode* root, int n0, int n1) {
    int n0_len = -1, n1_len = -1, ret = -1;
    function<TreeNode*(TreeNode*,int,int,int)> get_lca =
    [&](TreeNode* root, int n0, int n1, int cur) -> TreeNode* {
    if(!root) return nullptr;
    if(root->val == n0) {
      n0_len = cur;
      return root;
    } else if(root->val == n1) {
      n1_len = cur;
      return root;
    }
    auto left = get_lca(root->left, n0, n1, cur+1);
    auto right = get_lca(root->right, n0, n1, cur+1);
    if(left && right) {
      ret = n0_len + n1_len - 2 * cur;// remember to -2*cur
      return root;
    }
    return left ? left : right;
    };
    function<int(TreeNode*,int,int)> height = [&](TreeNode* root, int num, int curlen) {
      if(!root) return 0;
      if(root->val == num) return curlen;
      return max(height(root->left, num, curlen+1),
          height(root->right, num, curlen+1));
    };
    auto lca = get_lca(root, n0, n1, 0);

    if (n0_len != -1 && n1_len != -1) return ret;
    else if(n0_len != -1) return height(lca, n1, 0);// use return of lsa
    else if(n1_len != -1) return height(lca, n0, 0);
    else return -1;
  }
}