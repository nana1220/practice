/*
A number of buildings are visible form a point. Each building is a rectangle, and the bottom of each buliding lies on a
fixed line. A building is specified using a triple of (Left, Height, Right). One building may partly obstruct another, as shown below:
nv5RL1s
The skyline is the list of coordinates and corresponding heights of what is visible.
For example, the skyline to the buildings on the left in figure above is given in the figure on the right.
Assume you have a Building class:

struct Building {
    Building() {}
    Building(int l, int r, int h) : left(l), right(r), height(h){}
    int left{ 0 }, right{ 0 }, height{ 0 };
};
And the input is:
{{1,5,11}, {2,7,6}, {3,9,13}, {12,16,7}, {14,25,3}, {19,22,18}, {23,29,13}, {24,28,4}}

Output could be a set of merged buildings:
{{1,3,11}, {3,9,13}, {12,16,7}, {16,19,3}, {19,22,18}, {22,23,3}, {23,29,13}}
 */

// 类似归并排序 merge sort 思想的Divide and Conquer算法。首先原题需要给出skyline，也就是建筑物的轮廓，实际上就是合并所有相交的建筑物
// 合并效果大致就是二维版的merge intervals，也是本题的核心部分
// 对于没有相交的建筑物就按照merge sort的方法处理即可，最后得到的结果就是merge并排序后的建筑物，遍历一遍即可得到skyline。时间复杂度为O(NlogN)。

class Solu {
  using BuildingVec = vector < Building >;

  BuildingVec draw_skyline(BuildingVec buildings) {
    auto merge =[&](BuildingVec && a, BuildingVec && b){
      vector<Building> ret;
      int l = 0, r = 0;
      auto do_overlap =[&ret](Building & b0, Building & b1,int&i0,int&i1){
        if (b0.right <= b1.right) {
          if (b0.height > b1.height) {
            if (b0.right != b1.right) {
              ret.emplace_back(b0);
              b1.left = b0.right;
              ++i0;
            } else {
              ++i1;
            }
          } else if (b0.height == b1.height) {
            b1.left = b0.left;
            ++i0;
          } else {
            if (b0.left != b1.left)
              ret.emplace_back(b0.left, b1.left, b0.height);
            ++i0;
          }
        } else {
          if (b0.height >= b1.height) {
            ++i1;
          } else {
            if (b0.left != b1.left)
              ret.emplace_back(b0.left, b1.left, b0.height);
            b0.left = b1.right;
            ret.emplace_back(b1);
            ++i1;
          }
        }
      }
      ;
      while (l < a.size() && r < b.size()) {
        if (a[l].right < b[r].left)
          ret.emplace_back(a[l++]);
        else if (b[r].right < a[l].left)
          ret.emplace_back(b[r++]);
        else if (a[l].left <= b[r].left)
          do_overlap(a[l], b[r], l, r);
        else
          do_overlap(b[r], a[l], r, l);
      }
      while (l < a.size()) ret.emplace_back(a[l++]);
      while (r < b.size()) ret.emplace_back(b[r++]);
      return ret;
    } ;
    function<BuildingVec (int,int)>solve =[&](int l, int r) -> BuildingVec {
      if (l >= r) return {buildings.begin() + l, buildings.begin() + r + 1};
      int m = l + (r - l) / 2;
      return merge(solve(l, m), solve(m + 1, r));
    }
    ;
    return solve(0, (int) buildings.size() - 1);
  }
}