/*
LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. A subsequence
is a sequence that appears in the same relative order, but not necessarily contiguous.
For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
 */

class Solu{
  int lcs( char *X, char *Y, int m, int n ) {
    int L[m+1][n+1];
    int i, j;

    // Following steps build L[m+1][n+1] in bottom up fashion. Note
    // that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
    for (i=0; i<=m; i++) {
      for (j=0; j<=n; j++) {
        if (i == 0 || j == 0)
          L[i][j] = 0;
        else if (X[i-1] == Y[j-1])
          L[i][j] = L[i-1][j-1] + 1;
        else
          L[i][j] = max(L[i-1][j], L[i][j-1]);
      }
    }

    // L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1]
    return L[m][n];
    // use dfs to restore the LCS, in dp table go from dp[m][n] to the area with dp value 0,
    // can either go up and left, or go left, whenever find x[i]=y[j], store the letter and jump to x[i-1]y[j-1]
  }
}