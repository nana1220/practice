/*
Write an algorithm that counts the number of ways you can paint a fence with N posts using K colors such that no
 more than 2 adjacent fence posts are painted with the same color.
 */

/*
因为题目要求是不超过两个相邻的栅栏有同样颜色，所以可以把题目分解一下：
设T(n)为符合要求的染色可能总数，S(n)为最后两个相邻元素为相同颜色的染色可能数，D(n)为最后两个相邻元素为不同颜色的染色可能数。
显然D(n) = (k - 1) * (S(n-1) + D(n-1))
S(n) = D(n-1)
T(n) = S(n) + D(n)
带入化简一下得出：
T(n) = (k - 1) * (T(n-1) + T(n-2)), n > 2
 */

class Solu{
  int num_colors(int n, int k) {
    if(n <= 0 || k <= 0) return 0;
    int prev_prev = k, prev = k * k;
    for (int i = 0; i < n - 1; ++i) {
      int old_dif = prev;
      prev = (k - 1) * (prev_prev + prev);
      prev_prev = old_dif;
    }
    return prev_prev;
  }
}