/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is
formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 */

public class Solution {
  public int numIslands(char[][] grid) {
    if(grid.length ==0 || grid[0].length==0) return 0;
    int m=grid.length;
    int n=grid[0].length;
    int count=0;
    for(int x=0; x<m;x++){
      for(int y=0; y<n; y++){
        if(grid[x][y]=='1'){
          count++;
          fill(grid,x,y);
        }
      }
    }
    return count;
  }

  void fill(char[][] grid, int x, int y){
    int m=grid.length;
    int n=grid[0].length;
    if(grid[x][y]=='0') return;
    grid[x][y]='0';
    if(x+1 < m) fill(grid, x+1,y);
    if(x-1>=0) fill(grid,x-1,y);
    if(y+1<n) fill(grid,x,y+1);
    if(y-1>=0) fill(grid,x,y-1);
  }
}