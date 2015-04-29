/*
Given a rope of length n meters, cut the rope in different parts of integer lengths in a way that maximizes product
 of lengths of all parts. You must make at least one cut. Assume that the length of rope is more than 2 meters.
Examples:
Input: 2, return 1 because 1x1 = 1
Input: 5, return 6 because 2x3 = 6
 */

// 典型DP题，递推方程：dp(n) = max(i * (n - i), i * dp(n - i)), i = 1, 2, … , n-1
class Solu{
  int cut_rope(int n) {
    int dp[n+1];
    dp[0] = dp[1] = 1;

    for(int i = 2; i <= n; ++i) {
      dp[i] = 0;
      for(int j = 1; j < i; ++j) {
        dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j])); // max(j*(i-j), dp[j]*(i-j))
      }
    }
    return dp[n];
  }
}