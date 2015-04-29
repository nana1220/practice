class Solu{
  // get next node whose val is bigger than val
  TreeNode* get_next(TreeNode* root, int val) {
    if(!root) return nullptr;

    TreeNode *cur = root, *prev = nullptr;
    while(cur) {
      if(val < cur->val) {
        prev = cur;
        cur = cur->left;
      } else {
        cur = cur->right;
      }
    }
    return prev;
  }
}