/*
Given a string S and a string T, count the number of distinct subsequences of T in S.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the
characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
 */

// DFS: TLE O(2^n) n=S.length()
public class Solution {
  public int numDistinct(String S, String T) {
    return countDistinct(S,T,0,0);
  }

  int countDistinct(String S, String T, int s, int t ){
    if(t==T.length()) return 1;
    if(s==S.length()) return 0;
    int count = 0;
    for(int k = s; k< S.length(); k++){
      if(S.charAt(k) == T.charAt(t)){
        count += countDistinct(S, T, s+1, t+1) + countDistinct(S, T, s+1, t);
        break;
      }
    }
    return count;
  }
}

// Dynamic Programming
// dp[i][j] -- number of subsequences for S[0,i-1] and T[0,j-1]
// if S[i] = T[j] then dp[i][j] = dp[i-1]dp[j-1] + dp[i-1]dp[j] eithor use i to cover j or not use i to cover j
// if S[i] != T[j] dp[i][j] = dp[i-1]dp[j]
// time: O(m*n); space: O(m*n)
/*
关键是如何得到递推关系，可以这样想，设母串的长度为 i，
子串的长度为 j，我们要求的就是长度为 j 的字串在长度为 i 的母串中出现的次数，设为 t[i][j]，若母串的最后一个字符与子串的最后一个字符不同，
则长度为 j 的子串在长度为 i 的母串中出现的次数就是母串的前 i - 1 个字符中子串出现的次数，即 t[i][j] = t[i-1][j]，若母串的最后一个字符
与子串的最后一个字符相同，那么除了前 i - 1 个字符出现字串的次数外，还要加上子串的前 j - 1 个字符在母串的前 i - 1 个字符中出现的次数，
即 t[i][j] = t[i-1][j] + t[i - 1][j - 1]。
也可以用二维数组，这里图省事，直接用滚动数组了。
 */
public class Solution {
  public int numDistinct(String S, String T) {
    if (S==null || T==null)
      return 0;
    int M = S.length(), N = T.length();
    int[][] dp = new int[M+1][N+1];
    dp[0][0] = 1;  // both empty, initialize dp
    for (int i=1; i<=M; i++)
      dp[i][0] = 1; // T is empty, initialize dp
    for (int i=1; i<=M; i++){
      for (int j=1; j<=N; j++){
        dp[i][j] = dp[i-1][j]; // not use i-th char in S
        if (S.charAt(i-1)==T.charAt(j-1))
          dp[i][j] += dp[i-1][j-1]; // use i-th char in S
      }
    }
    return dp[M][N];
  }
}