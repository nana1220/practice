/*
 Given a string s, partition s such that every substring of the partition is a palindrome.
 Return the minimum cuts needed for a palindrome partitioning of s.
 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/


// Use a 2d matrix to reduce palindrome checking from O(n) to O(1)
// time: O(n^2); space: O(n^2)
public class Solution {
  public int minCut(String s) {
    if (s==null || s.length()==0)
      return 0;
    int N = s.length();
    int[] dp = new int[N+1];
    boolean[][] isP = new boolean[N][N];
    for (int i=0; i<N; i++)
      isP[i][i] = true;
    // if the whole string is a palindrome, we still increment by 1. So, we need to set initial value to -1
    dp[0] = -1;
    for (int i=1; i<=N; i++){
      dp[i] = i-1; // at most cut i-1 to make a length i string has i partition, each length 1 string must be palindrome
      for (int j=0; j<=i-1; j++){// j=0 means no cut, j means leave j chars the left and check if right part is a palindrome, dp[j] is the min cut for left part
        if (s.charAt(j)==s.charAt(i-1) &&(j+1>i-2 || isP[j+1][i-2])){
          isP[j][i-1] = true;
          dp[i] = Math.min(dp[i], dp[j]+1); // right part count 1 cut
        }
      }
    }
    return dp[N];
  }
}


// dp: use two dp, one dp for mincut, another dp for check palindrome
// palin[i][j] denote string[i,j] is palindrome
public class Solution {
  public int minCut(String s) {
    int len = s.length();
    boolean[][] isPalin = new boolean[len+1][len+1];
    for (int k=0; k<=len; k++) isPalin[k][k] =true;
    int[] dp = new int[len+1]; // NOTE: when use dp, remember to check if need array of [len+1]
    dp[0] =-1; //NOTE: length 1 string needs 0 cut, dp[1]=1+dp[0], so d[0] needs to initialize to -1
    for(int i=1; i<=len; i++){
      int min=i; //NOTE!!!!!: at most cut i partition for a length i string
      for(int j=1; j<=i; j++){
                                                            // j+1 > i-1 handle the case of length 1 and length 2
        isPalin[j][i] = (s.charAt(j - 1) == s.charAt(i - 1)) && (j+1 > i-1 || isPalin[j + 1][i - 1]); // palindrome dp propagation formula
        if(isPalin[j][i]) min = Math.min(min, dp[j-1]);
      }
      dp[i] = min +1;
    }
    return dp[len];
  }
}
