/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
 */

// 2D dp
public class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    int m=s1.length();
    int n=s2.length();
    if(m+n != s3.length()) return false;
    if(m==0) return s2.equals(s3);
    if(n==0) return s1.equals(s3);
    // s1[i] first i chars of s1, s2[j] first j chars of s2, s3[i+j] first (i+j) chars of s3
    // dp[i][j] represents if first i and first j isInterleaving string of first i+j
    boolean[][] dp = new boolean[m+1][n+1];
    dp[0][0] = true;
    // NOTE!!!!: dp[0][1], dp[1][0] are not enough, 2D dp initialization needs all dp[0][j] and dp[i][0]
    for (int i=1; i<=m && s1.charAt(i-1)==s3.charAt(i-1); i++)  dp[i][0] = true;
    for (int j=1; j<=n && s2.charAt(j-1)==s3.charAt(j-1); j++)  dp[0][j] = true;

    for(int i=1; i<=m; i++){
      for(int j=1; j<=n; j++){
        boolean res = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
        res |= dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
        dp[i][j] = res;
      }
    }
    return dp[m][n];
  }
}
