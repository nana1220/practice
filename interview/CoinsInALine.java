/*
There are n coins in a line. (Assume n is even). Two players take turns to take a coin from one of
the ends of the line until there are no more coins left. The player with the larger amount of money wins.

Would you rather go first or second? Does it matter?
Assume that you go first, describe an algorithm to compute the maximum amount of money you can win.
 */
//一串数，两个玩家，每次轮流选一个数并且只能选最左或最右，在你先选并且对手很聪明的情况下，怎样max你的和。
//    Example:
//    {5, 3, 7, 10}，应该返回15
//    {8, 15, 3, 7}，应该返回22

/*
http://articles.leetcode.com/2011/02/coins-in-line.html
 */
/*
DP经典题。每一步我们都有一头一尾两个决策，最优解就是看选完之后随后的是否是最优解。用一个二维数组dp[i][j]保存原数组i到j中的最优解，
从一个数字开始扫，长度逐渐递增。递推公式如下：

当j == i时
dp(i, j) = coins[i]
当j == i + 1时
dp(i, j) = max(coins[i], coins[j])
当j - i >= 3时
dp(i, j) = max(coins[i] + min(dp(i+2, j), dp(i+1, j-1) ),
               coins[j] + min(dp(i+1, j-1), dp(i, j-2) ))
 */
class Solu{
  int coins_in_line(const vector<int>& arr) {
    if(arr.empty()) return 0;
    int n = (int)arr.size();
    int dp[n][n];

    for(int len = 0; len < n; ++len) {
      for(int i = 0, j = len; j < n; ++i,++j) { // for(int len=2; len< n; len++) { for(int i=0; i + len<n; i++) {dp[][]=} }
        if(i == j) {
          dp[i][j] = arr[i];
        } else if(i+1 == j) {
          dp[i][j] = max(arr[i], arr[j]);
        } else {
          dp[i][j] = max(arr[i] + min(dp[i+1][j-1], dp[i+2][j]),
              arr[j] + min(dp[i][j-2], dp[i+1][j-1]));
        }
      }
    }
    return dp[0][n-1];
  }
}
