/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */

// dp: go from (m-1,n-1) to (0,0)
// bottom up, sums[i][j] is the minimum sum from (0,0) to (i,j)
public class Solution {
  public int minPathSum(int[][] grid) {
    int m=grid.length;
    int n= grid[0].length;
    int[][] sums = new int[m][n];
    sums[0][0]=grid[0][0];
    for(int i=1; i< m;i++){
      sums[i][0]= sums[i-1][0]+grid[i][0];
    }
    for(int i=1; i<n; i++){
      sums[0][i] = sums[0][i-1] + grid[0][i];
    }
    for (int i=1; i<m; i++){
      for(int j=1; j<n; j++){
        sums[i][j] = grid[i][j] +Math.min(sums[i-1][j], sums[i][j-1]);
      }
    }
    return sums[m-1][n-1];
  }
}