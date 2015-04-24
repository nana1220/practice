/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner
of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
 */

public class Solution {
  public int uniquePaths(int m, int n) {
    //return dfs(m,n);
    return dp(m,n);
  }
  // TLE
  int dfs(int m, int n){
    if(m==1 && n==1) return 1;
    int count=0;
    if(m>1) count += dfs(m-1,n);
    if(n>1) count += dfs(m, n-1);
    return count;
  }
  // dp: go back from (m,n) to (1,1), each cube represents number of path from this cube to (1,1)
  // bottom up from (1,1) to (m,n), paths[1][1]=1,cubes in first row and col are all 1
  int dp(int m, int n){
    int[][] paths = new int[m+1][n+1];
    for(int i=1; i<=m; i++) paths[i][1]=1;
    for(int i=1; i<=n; i++) paths[1][i]=1;
    for(int i=2; i<=m; i++) {
      for(int j=2;j<=n;j++){
        paths[i][j] =paths[i-1][j]+paths[i][j-1];
      }
    }
    return paths[m][n];
  }
}