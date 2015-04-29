/*
You are given two balanced binary search trees. Write a function that merges the two given balanced BSTs into a balanced binary search tree.
Your merge function should take O(M+N) time and O(1) space.
 */

// 可以先把两个输入BST给转换成链表，然后merge两个链表，再把merge完的链表重新转化成BST。
class Solu{
  TreeNode* flatten_bst(TreeNode* root) {
    if(!root) return nullptr;

    auto left = flatten_bst(root->left), right = flatten_bst(root->right);

    auto left_tail = left;
    while (left_tail && left_tail->right) left_tail = left_tail->right;

    if(left_tail) left_tail->right = root;
    root->left = nullptr;
    root->right = right;
    return left ? left : root;
  }
  TreeNode* merge_bst_list(TreeNode* a, TreeNode* b) {
    if(!a || !b) return a ? a : b;
    if(a->val < b->val) {
      a->right = merge_bst_list(a->right, b);
      return a;
    } else {
      b->right = merge_bst_list(a, b->right);
      return b;
    }
  }
  TreeNode* middle(TreeNode* nd, TreeNode* end) {
    auto fast = nd, slow = nd;
    while (fast!=end && fast->right!=end) {
      fast = fast->right->right;
      slow = slow->right;
    }
    return slow;
  };
  TreeNode* bst_from_list(TreeNode* head) {
    if(!head) return nullptr;

    function<TreeNode*(TreeNode*,TreeNode*)> convert = [&](TreeNode *begin, TreeNode *end) -> TreeNode*{
    if(!begin || begin == end) return nullptr;
    if(begin->right == end) {
      begin->left = begin->right = nullptr;
      return begin;
    }
    auto mid = middle(begin, end);
    mid->left = convert(begin, mid);
    mid->right = convert(mid->right,end);
    return mid;
    };
    return convert(head, nullptr);
  }
  TreeNode* merge_bst(TreeNode* a, TreeNode* b) {
    if(!a || !b) return a ? a : b;
    auto la = flatten_bst(a), lb = flatten_bst(b);
    return bst_from_list(merge_bst_list(la, lb));
  }
}