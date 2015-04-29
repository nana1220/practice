/*
假设你是harry potter，在grid的左上角，你现在要走到右下角，grid中有正数也有负数，遇到正数表示你的strength增加那么多，遇到负数
表示strength减少那么多，在任何时刻如果你的strength小于等于0，那么你就挂了。在一开始你有一定的初始的strength，现在问这个初始
的strength最少是多少，才能保证你能够找到一条路走到右下角。每一步只能向右或者向下。
 */

// DP题，可以从右下角往左上角扫。递推公式如下：dp[i][j] = max(1, min(dp[i][j+1] - mat[i][j], dp[i+1][j] - mat[i][j]))
class Solu{
  int min_hp(const vector<vector<int> > &mat) {
    if(mat.empty() || mat[0].empty()) return 0;
    int m = (int)mat.size(), n = (int)mat[0].size();
    vector<vector<int>> dp(m, vector<int>(n));

    for(int i = m-1; i >= 0; --i) {
      for(int j = n-1; j >= 0; --j) {
        if(i == m-1 && j == n-1) {
          dp[i][j] = max(1, 1 - mat[i][j]);
        } else {
          int right = j == n-1 ? INT_MAX : dp[i][j+1] - mat[i][j];
          int down = i == m-1 ? INT_MAX : dp[i+1][j] - mat[i][j];
          dp[i][j] = max(1, min(right,down));
        }
      }
    }
    return dp[0][0];
  }
}