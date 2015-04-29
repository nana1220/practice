// in order traveral, make a copy of each node
class Solu{
  DoublyListNode* bst_to_list(TreeNode* root) {
    DoublyListNode dum; // dummy head
    auto cur = &dum;

    function<void(TreeNode*)> dfs = [&](TreeNode* node) {
      if(!node) return;
      dfs(node->left);

      auto new_node = new DoublyListNode(node->val);
      cur->next = new_node;
      new_node->prev = cur;
      cur = new_node;

      dfs(node->right);
    };

    dfs(root);

    if(dum.next) dum.next->prev = nullptr;
    return dum.next;
  }
}