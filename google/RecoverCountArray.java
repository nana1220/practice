/*
给定一个数字数组 ,其中每个元素是从末端数小于原数组中该元素的个数。求原数组。
原数组中元素是一个[1,n]的随机排列。

For example:
Count array: [3, 0, 1, 0]
Original array: [4, 1, 3, 2]

Can you give an O(nlogn) solution?
实际上就是求O(logn)的第k小的元素。问题可以转化为带count数的BST或者线段树。
本题跟另外一个G家的面试题也异曲同工：
给一个数组a[n]，
令s[i]为a[i+1..n-1]中比a[i]大的数的数量。求最大的s[i]。要求O(nlogn)
 */


class solu {
  class Segment_Tree {
    private :
    struct Node

    {
      int left, right, cover;
    }

    ;
    Node Tree[
    Maxn];
    int Number, c, d;
    public :

    void build(int Now, int a, int b) {
      Tree[Now].left = a;
      Tree[Now].right = b;
      if (b - a >= 1) {
        int mid = (a + b) >> 1;
        Tree[Now].left = a;
        build(2 * Now + 1, a, mid);
        Tree[Now].right = b;
        build(2 * Now + 2, mid + 1, b);
        Tree[Now].cover = Tree[2 * Now + 1].cover + Tree[2 * Now + 2].cover;
      } else Tree[Now].cover = 1;
    }

    void del(int Num, int x) {
      if (Tree[Num].left <= x && Tree[Num].right >= x) {
        Tree[Num].cover--;
        if (Tree[Num].left < Tree[Num].right) {
          del(2 * Num + 1, x);
          del(2 * Num + 2, x);
        }
      }
    }

    int Count(int Num, int x) {
      if (Tree[Num].right == Tree[Num].left) return Tree[Num].left;
      else {
        if (Tree[2 * Num + 1].cover >= x)
          return Count(2 * Num + 1, x);
        else
          return Count(2 * Num + 2, x - Tree[2 * Num + 1].cover);
      }
    }

  };

  int main() {
    int n;
    int a, b;

    Segment_Tree s;
    cin >> n;
    for (int i = 0; i < n; i++) {
      cin >> val[i];
    }
    s.build(0, 0, n - 1);
    for (int i = 0; i < n; i++) {
      int x = s.Count(0, val[i] + 1);
      cout << x + 1 << endl;
      s.del(0, x);
    }
    return 0;
  }
}

/*
 Count array 就是一个rank
: 表示当前数字在还存在的[1..n]中的第几个
:  count array
: i  C[3,0,1,0]   N[1,2,3,4]
: 0 C[0] = 3     N中第3个,N[3] = 4,在N里面删除4, N=[1,2,3]
: 1 C[1] = 0     N中第0个,N[0] = 1,在N里面删除1, N=[2,3]
: 2 C[2] = 1     N中第1个,N[1] = 3,在N里面删除3, N=[2]
: 3 C[3] = 0     N中第0个,N[0] = 2
: 所以答案是4 1 3 2
: 我们需要一个算法快速的得到[1..n]中第k个，并且还要快速的删除掉，我只会用线段

线段树嘛大概这样，我们建立一个[1..n]这个区间的线段树，每个叶子节点标记为1，
其他节点的值为这个节点下面有多少个为1的叶子节点。

【查找k大】
看左子树有多少个为1的节点，如果大于等于k，那么就在左子树找。如果不到k，那么
就在右子树找k-左子树为1的叶子节点个数。
当你找到相应的叶子节点，那么他表示的区间[l,r](l == r)，l或者r就是我们要找的[
1..n]里面的第k个数啦。
【删除】
就是把那个叶子节点标记为0，其他包含这个节点的区间当然就是num--
 */

class Solu{
  struct TreeNode {
    TreeNode *left, *right;
    int val;
    int l, r;
    TreeNode (int _l, int _r) : l(_l), r(_r), left(nullptr), right(nullptr) {
    }
    TreeNode (int _val, int _l, int _r) : val(_val), l(_l), r(_r), left(
        nullptr), right(nullptr) {
    }
  };

  TreeNode* buildTree(int l, int r) {
    TreeNode* root = new TreeNode(l, r);
    if (l == r) {
      root->val = 1;
      return root;
    }
    int mid = (l + r) / 2;
    root->left = buildTree(l, mid);
    root->right = buildTree(mid + 1, r);
    root->val = root->left->val + root->right->val;
    return root;

  }

  int query(TreeNode* root, int k) {
    if (root->l == root->r) return root->l;
    if (root->left->val >= k) return query(root->left, k);
    else return query(root->right, k - root->left->val);
  }

  void update(TreeNode* root, int num) {
    if (root->l <= num && root->r >= num) {
      root->val--;
      if (root->l < root->r) {
        update(root->left, num);
        update(root->right, num);
      }
    }
  }

  int main() {
    int n;
    cin >> n;
    TreeNode* root = buildTree(1, n);
    for (int i = 0; i < n; i++) {
      int num;
      cin >> num;
      int x = query(root, num + 1);
      cout << x << " ";
      update(root, x);
    }
    cout << endl;
    return 0;
  }
}

class Solu{

  // 线段树代码
  class SegmentTree {
    public:
    struct Node {
      Node* left{ nullptr };
      Node* right{ nullptr };
      int begin{ 0 };
      int end{ 0 };
      int cover{ 0 };

      bool is_leaf() { return begin == end; }

      Node(int l, int r) :begin(l), end(r), cover(r-l+1) {}
      ~Node() {
        if(left) delete left;
        if(right) delete right;
      }
    };

    SegmentTree(int range) : _range(range) {
      _root = build(0, range - 1);
    }

    ~SegmentTree() {
      delete _root;
    }

    void remove_leaf(int val) {
      if(!find_leaf(val)) return;
      remove_impl(_root, val);
    }

    bool find_leaf(int val) {
      Node* cur = _root;
      while(cur) {
        if(cur->begin == val && cur->end == val) return true;
        int mid = cur->begin + (cur->end - cur->begin) / 2;

        if(val <= mid) {
          cur = cur->left;
        } else {
          cur = cur->right;
        }
      }
      return false;
    }

    int get_kth(int k) {
      Node* cur = _root;
      while(cur) {
        if(k == 0 && cur->is_leaf()) return cur->begin;

        int left_cover = cur->left ? cur->left->cover : 0;
        if(k < left_cover) {
          cur = cur->left;
        } else {
          k -= left_cover;
          cur = cur->right;
        }
      }
      return -1;
    }

    private:
    Node* build(int left, int right) {
      if(left > right) return nullptr;
      auto ret = new Node(left, right);
      if(left == right) return ret;

      int mid = left + (right - left) / 2;
      ret->left = build(left, mid);
      ret->right = build(mid+1, right);

      return ret;
    }

    void remove_impl(Node* root, int val) {
      if(!root) return;
      --root->cover;

      if(root->left && root->left->begin == val && root->left->end == val) {
        delete root->left;
        root->left = nullptr;
      } else if(root->right && root->right->begin == val && root->right->end == val) {
        delete root->right;
        root->right = nullptr;
      }
      int mid = root->begin + (root->end - root->begin) / 2;
      if(val <= mid) {
        remove_impl(root->left, val);
      } else {
        remove_impl(root->right, val);
      }
    }

    Node* _root{ nullptr };
    int _range{ 0 };
  };

  // 题解代码
  vector<int> recover_array(const vector<int>& arr) {
    SegmentTree st((int)arr.size());

    vector<int> ret;
    for (auto n : arr) {
      int kth = st.get_kth(n);
      ret.push_back(kth + 1);
      st.remove_leaf(kth);
    }
    return ret;
  }
}