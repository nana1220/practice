/*
Given a BST and a number x, find two nodes in the BST whose sum is equal to x.
You can’t use extra memory like converting BST into one array and then solve this like 2sum.
 */

// brute force O(n^2) two loops in order traverse
class SOlu {
  TreeNode* find_val(int val, TreeNode*origin, TreeNode*node) {
    if (!node) return nullptr;
    auto n = find_val(val, origin, node -> left);
    if (n) return n;
    if (node -> val == val && node != origin) return node;
    return find_val(val, origin, node -> right);
  }

  pair<TreeNode*,TreeNode*>

  bst_2_sum(int sum, TreeNode*node) {
    if (!node) return {nullptr, nullptr};

    auto r = bst_2_sum(sum, node -> left);
    if (r.first && r.second) return r;

    auto other = find_val(sum - node -> val, node, root);
    if (other) return {node, other};

    if (sum >= node -> val) {
      r = bst_2_sum(sum, node -> right);
      if (r.first && r.second) return r;
    }
    return {nullptr, nullptr};

  }
}

/*
O(n) two pointers, left and right
 we only require at max 2*height_of_BST, in first array of size height_of_BST, we store all elements which comes from
root->left,root->left->left,.......
and in 2nd array we store
root,root->right,root->right->right,.......
and we start with the last element of these two array, these array's are actually stack.
 */
class SOlu{

}

