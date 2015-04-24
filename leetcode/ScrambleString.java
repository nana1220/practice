/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */

// DFS, TLE
public class Solution {
  // dfs, leafs of s1 equals to s2
  // given a partition of s2, either s11=s21, s12=s22 or swapped s11' = s21, s12' = s22, that is,
  // either s11 isScramble s21, s12 isScramble s22 or s11' isScramble s21, s12' isScramble s22
  public boolean isScramble(String s1, String s2) {
    int len = s1.length();
    if(s1.equals(s2)) return true;
    for(int i=1; i<s1.length();i++){
      String s11= s1.substring(0,i);
      String s12 = s1.substring(i);
      String s21 = s2.substring(0,i);
      String s22 = s2.substring(i);
      if(isScramble(s11, s21) && isScramble(s12,s22)) return true;
      s11 = s1.substring(len-i);
      s12 = s1.substring(0, len-i);
      if(isScramble(s11, s21) && isScramble(s12,s22)) return true;
    }
    return false;
  }
}
// 3D dp
public class Solution {
  public boolean isScramble(String s1, String s2) {
    int len= s1.length();
    boolean[][][] dp = new boolean[len+1][len][len];
    for(int l=1; l<=len; l++){
      for(int i=0;i<=len-l;i++){ //NOTE:!!!!!!! partition is only valid when i <= len -l
        for(int j=0;j<=len-l ;j++){ // same as i
          if(l==1) dp[l][i][j]= s1.charAt(i)==s2.charAt(j);
          for(int k =1; k<l;k++){
            dp[l][i][j] = ((dp[k][i][j] && dp[l-k][i+k][j+k]) || (dp[k][i+l-k][j] && dp[l-k][i][j+k]));
            if(dp[l][i][j]) break;
          }
        }
      }
    }
    return dp[len][0][0]; // because string length = len, when l = len, only dp[len][0][0] is valid, and this is the last term computed
  }
}



