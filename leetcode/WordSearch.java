/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */

public class Solution {
  public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    for(int i=0; i<m;i++){
      for(int j=0;j<n;j++){
        // add check if(board[i][j]==word.charAt(0)) can save call overhead
        if(exist(board,word,i,j,0,visited)) return true;
      }
    }
    return false;
  }

  boolean exist(char[][] board, String word, int x, int y, int idx, boolean[][] visited){
    // NOTE: check with length()-1 instead of length() can handle length=1 case, that is, case with no move
    if(board[x][y] != word.charAt(idx)) return false;
    if(idx == word.length()-1) return true;
    int m = board.length;
    int n = board[0].length;
    visited[x][y] = true;

      if(x+1 <m && !visited[x+1][y]){
        if(exist(board, word, x+1, y, idx+1,visited)) return true;
      }
      if(x-1 >=0 && !visited[x-1][y]){
        if(exist(board,word,x-1,y,idx+1,visited)) return true;
      }
      if(y+1<n && !visited[x][y+1]){
        if(exist(board,word,x,y+1,idx+1,visited)) return true;
      }
      if(y-1 >=0 && !visited[x][y-1]){
        if(exist(board,word,x,y-1,idx+1,visited)) return true;
      }

    visited[x][y] = false;
    return false;
  }
}