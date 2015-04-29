/*
Given a binary search tree (BST), find the lowest common ancestor of two given nodes in the BST.
 */

class solu{
  TreeNode *LCA(TreeNode *root, TreeNode *p, TreeNode *q) {
    if (!root || !p || !q) return nullptr;
    if (max(p->val, q->val) < root->val)
      return LCA(root->left, p, q);
    else if (min(p->val, q->val) > root->val)
      return LCA(root->right, p, q);
    else
      return root;
  }
}