/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
 */

public class Solution {
  public int totalNQueens(int n) {
    char[][] board = new char[n][n];
    return solve(board, 0);

  }

  int solve(char[][] board, int row) {
    int count=0;
    int n =board.length;
    if (row == board.length){
      return 1;
    }
    for(int col=0; col<n; col++) { // in the case no col is valid, for loop is skiped and return count =0
      if(check(board, row, col)) {
        board[row][col] = 'Q';
        count += solve(board, row+1);
        board[row][col] = '.';
      }
    }
    return count;
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