/*
Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if length of the rod
is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)

Example:
Pricing list: {1, 5, 8, 9, 10, 17, 17, 20}
Result = 22 (cut into two pieces of length 2 and 6)
 */

// DP，跟Maximum Product Cutting思路非常相似，递推方程如下：dp(n) = max(prices[i-1] + dp(n-i)), i = 1,2,…,n-1
class Solu{
  int cut_rod(const vector<int>& p) {
    int n = (int)p.size(), dp[n + 1];
    dp[0] = 1; // dp[1] = p[1]

    for(int i = 1; i <= n; ++i) {
      dp[i] = p[i-1];
      for(int j = 1; j < i; ++j)
        dp[i] = max(dp[i], p[j-1] + dp[i-j]);
    }
    return dp[n];
  }
}