// 没有parent指针的版本。

class Solu{
  TreeNode *LCA(TreeNode *root, TreeNode *p, TreeNode *q) {
    if (!root) return NULL;
    if (root == p || root == q) return root;
    TreeNode *L = LCA(root->left, p, q);
    TreeNode *R = LCA(root->right, p, q);
    if (L && R) return root;
    return L ? L : R;
  }
}

// 有parent指针的版本。
class solu{
  int getHeight(TreeNode *p) {
    int height = 0;
    while (p) {
      height++;
      p = p->parent;
    }
    return height;
  }
  TreeNode *LCA(TreeNode *p, TreeNode *q) {
    int h1 = getHeight(p);
    int h2 = getHeight(q);
    if (h1 > h2) {
      swap(h1, h2);
      swap(p, q);
    }
    int dh = h2 - h1;
    for (int h = 0; h < dh; h++) // let the deeper node goes to the same level as higher node
      q = q->parent;

    while (p && q) { // then find parent until find same parent
      if (p == q) return p;
      p = p->parent;
      q = q->parent;
    }
    return nullptr;
  }
}