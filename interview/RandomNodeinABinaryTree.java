/*
Randomly return a node in a binary tree,
 */

// resevoir sampling
// do a whatever order of travel, if it is the kth node we are traveling, then replace the previously selected node with probability 1/k.
class SOlu{
  // pre order traversal, first node is root
  void randNodeImp(const TreeNode * node, int & idx, const TreeNode * & r) {
    if (!node) return;
    if (rand(idx) == 0)
      r = node;
    randNodeImp(node->left,  ++idx, r);
    randNodeImp(node->right, ++idx, r);
  }

  const TreeNode * randNode(const TreeNode * root) {
    int idx = 1;
    const TreeNode * r = 0;
    randNodeImp(root, idx, r);
    return r;
  }
}