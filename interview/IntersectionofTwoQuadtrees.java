/*
Given a quadtree structure:
struct QuadNode {
    QuadNode(int num_ones = 0) : ones(num_ones) {}
    int ones{ 0 };
    QuadNode* child[4]{ nullptr };
};
Please build a quadtree to represent a 0-1 matrix, assume the matrix is a square and the dimension is power of 2.
Given two such quadtrees with same depth, please write a function to calculate how many 1s are overlapped.
For example:

Matrix 0:
0 1
1 1
Matrix 1:
0 0
1 1
Your function should return 2.
 */



class Solu{
  // Build tree
  QuadNode* build_tree(const vector<vector<int>>& mat) {
    if(mat.empty()) return nullptr;
    function<QuadNode*(int,int,int)> build = [&](int size, int row, int col) {
      if (size == 1) return new QuadNode(mat[row][col]); // leaf node initialized with the value in the corresponding cell
      auto root = new QuadNode(); // current node
      size /= 2;
      int sub_coords[4][2] = {{row, col},{row + size, col}, // top-left points of four sub squares
        {row, col + size},{row + size, col + size}};
      for(int i = 0; i < 4; ++i) {
        root->child[i] = build(size, sub_coords[i][0], sub_coords[i][1]);
        root->ones += root->child[i]->ones;
      }
      return root;
    };
    return build((int)mat.size(), 0, 0);
  }

  // Calculate intersection
  // 在计算交集的时候，如果比较的两个节点有一个节点的ones等于0，则该子树可以直接砍掉，这就是四叉树的高效之处。
  int intersections(QuadNode *t0, QuadNode *t1) {
    function<int(QuadNode*,QuadNode*)> solve = [&](QuadNode *tree0, QuadNode* tree1) {
      if(!tree0 || !tree1 || !tree0->ones || !tree1->ones)
        return 0;
      int sum = 0;
      //因为保证矩阵尺寸为power of 2，所以如果有子节点，那么四个子节点都不为NULL，如果没有，则都为NULL。所以只需要check child[0]。
      if (!tree0->child[0] && !tree1->child[0]) { // leaf node, no children
        sum += tree0->ones && tree1->ones ? 1 : 0;
      } else {
        for(int i = 0; i < 4; ++i)
          sum += solve(tree0->child[i], tree1->child[i]);
      }
      return sum;
    };
    return solve(t0, t1, 0);
  }


  int intersections(QuadNode *t0, QuadNode *t1) {
    // I think sum parameter is useless
    function<int(QuadNode*,QuadNode*,int)> solve = [&](QuadNode *tree0, QuadNode* tree1, int sum) {
      if(!tree0 || !tree1 || !tree0->ones || !tree1->ones)
        return 0;
      int ret = sum;
      if (!tree0->child[0] && !tree1->child[0]) { // leaf node, no children
        ret += tree0->ones && tree1->ones ? 1 : 0;
      } else {
        for(int i = 0; i < 4; ++i)
          ret += solve(tree0->child[i], tree1->child[i], sum);
      }
      return ret;
    };
    return solve(t0, t1, 0);
  }
}