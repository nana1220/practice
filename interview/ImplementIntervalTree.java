class SOlu{
  struct IntervalNode {
    int startPos;
    IntervalNode * left, * right, *parent;

    IntervalNode(int pos = 0, IntervalNode * par = NULL) {
      startPos = pos;
      left = right = NULL;
      parent = par;
    }
  };
  class IntervalTree {
    IntervalNode * root;
    int intervalSize;

    void insert( IntervalNode * curNode, int pos ) {
      if (pos < curNode->startPos) {
        if (curNode->left) insert( curNode->left , pos );
        else curNode->left = new IntervalNode(pos, curNode);
      }
      else {
        if (curNode->right) insert( curNode->right , pos );
        else curNode->right = new IntervalNode(pos, curNode);
      }
    }
    bool find(const IntervalNode * cur, int pos) {
      if (cur == NULL)
        return false;
      if (cur->startPos == pos ) return true;
      else if (cur->startPos > pos ) return find(cur->left, pos );
      else return find(cur->right, pos);
    }
    void erase( IntervalNode * cur, int pos ) {
      if (cur == NULL)
        return;
      if (cur->startPos == pos) {
        IntervalNode * tmp = cur, * par = cur->parent, **ptr;
        if (par == NULL)     // root
          ptr = &root;
        else
        ptr = (par->left == cur) ? &par->left : &par->right;

        if (cur->right == NULL)
        *ptr = cur->left;
        else {
          *ptr = cur->right;
          if (cur->left != NULL) {
            IntervalNode * leftsubtree = cur->right->left, * it;
            cur->right->left = cur->left;
            cur = cur->right;
            cur->left->parent = cur;
            cur->parent = par;
            it = cur->left;
            while (it->right)
              it = it->right;
            it->right = leftsubtree;
          }
        }
        delete tmp;
      }
      else if (cur->startPos < pos) erase( cur->left, pos);
      else erase( cur->right, pos);
    }
    public:
    IntervalTree() {
      intervalSize = -1;
      root = NULL;
    }
    void insert( int start, int end ) {
      if (root == NULL) {
        intervalSize = end-start;
        root = new IntervalNode(start);
      }
      else
        insert(root, start);
    }
    bool find(int start, int end) {
      return find( root, start );
    }
    void erase(int start, int end) {
      erase(root, start);
    }
  };
}