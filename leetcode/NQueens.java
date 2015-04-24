/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */

public class Solution {
  public List<String[]> solveNQueens(int n) {
    List<String[]> res = new ArrayList<String[]>();
    char[][] board = new char[n][n];
    solve(board, 0, res);
    return res;
  }

  void solve(char[][] board, int row, List<String[]> res) {
    int n =board.length;
    if (row == board.length){
      res.add(boardToRes(board));
      return;
    }
    for(int col=0; col<n; col++) {
      if(check(board, row, col)) {
        board[row][col] = 'Q';
        solve(board, row+1, res);
        board[row][col] = '.';
      }
    }
  }
  String[] boardToRes(char[][] board) {
    int n=board.length;
    String[] res = new String[n];
    for (int i=0; i< n; i++) {
      StringBuilder sb = new StringBuilder();
      for(int j=0; j<n; j++) {
        if(board[i][j] =='Q') sb.append('Q');
        else sb.append('.');
      }
      res[i] = sb.toString();
    }
    return res;
  }
  boolean check(char[][] board, int x, int y) {
    int n = board.length;
    for (int i=0; i<n; i++) {
      if(i != y && board[x][i] == 'Q'){
        return false;
      }
      if (i != x && board[i][y] =='Q') {
        return false;
      }
    }
    int i =1;
    while(x+i < n && y-i>=0){
      if(board[x+i][y-i] == 'Q') return false;
      i++;
    }
    i=1;
    while(x-i>=0 && y+i <n) {
      if(board[x-i][y+i] =='Q') return false;
      i++;
    }
    i=1;
    while(x-i >=0 && y-i>=0){
      if(board[x-i][y-i] =='Q') return false;
      i++;
    }
    i=1;
    while(x+i <n && y+i < n) {
      if(board[x+i][y+i] =='Q') return false;
      i++;
    }
    return true;
  }
}
