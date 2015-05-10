/*
给你一个字符矩阵，求出它的最小覆盖子矩阵，即使得这个子矩阵的无限复制扩张之后的矩阵，能包含原来的矩阵。 即二维的最小覆盖子串。
比如如下矩阵：
1
2
{ "ABABA",
  "ABABA" }
其最小覆盖子矩阵为AB，长度为2，故返回2。
 */

/*
首先先考虑如何计算一维字符串的最小覆盖子串长度：
对于某个字符串s，它的最小覆盖子串指的是长度最小的子串p，p满足通过自身的多次重复得到q，且s为q的子串。
这个可以通过KMP算法的prefix数组(next数组)得出。最小覆盖子串长度 = n - next[n-1]

先求出每一行最小覆盖子串的长度，取所有行算出来结果的的最小公倍数，得出最小覆盖矩阵的宽度。
再求出每一列的最小覆盖子串的长度，再求最小公倍数，就可以获得最小覆盖矩阵的高度了。两个相乘就是面积。
 */

class Solu{
  int min_cover_mat(const vector<string>& map) {
    if(map.empty() || map[0].empty()) return 0;
    int rows = (int)map.size(), cols = (int)map[0].size();

    auto lcm = [](int a, int b){
      int mul = a * b;
      for(int r = a % b; r ;){
        a = b;
        b = r;
        r = a % b;
      }
      return mul / b;
    };
    auto num_cover_substr_col = [&](int r) {
      int next[cols+1], i = 0, j = -1;
      next[0] = -1;
      while(i < cols){
        if(j == -1 || map[r][i] == map[r][j]){
          ++i, ++j;
          next[i] = j;
        } else j = next[j];
      }
      return i - next[i];
    };
    auto num_cover_substr_row = [&](int c) {
      int next[rows+1], i = 0, j = -1;
      next[0] = -1;
      while(i < rows){
        if(j == -1 || map[i][c] == map[j][c]){
          ++i, ++j;
          next[i] = j;
        } else j = next[j];
      }
      return i - next[i];
    };

    int r_nums = 1, c_nums = 1;

    for(int r = 0; r < rows; r ++){
      c_nums = lcm(c_nums, num_cover_substr_col(r));
      if(c_nums > cols){
        c_nums = cols;
        break;
      }
    }
    for(int c = 0; c < c_nums; c ++){
      r_nums = lcm(r_nums, num_cover_substr_row(c));
      if(r_nums > rows){
        r_nums = rows;
        break;
      }
    }
    return r_nums * c_nums;
  }
}