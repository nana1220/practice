/*
Write a program to solve a Sudoku puzzle by filling the empty cells.
Empty cells are indicated by the character '.'.
You may assume that there will be only one unique solution.
*/

public class Solution {
  public void solveSudoku(char[][] board) {
    solver(board, 0, 0);

  }

  boolean solver(char[][] b, int x, int y){
    if (x == 8 && y ==9) return true;
    if (y ==9) return solver(b, x+1, 0);    // scan line by line, when hit the line end, go to next line
    if (b[x][y] != '.') return solver(b, x, y+1);
    HashSet<Character> available = getAvailable(b, x, y);
    for (char c : available) {
      b[x][y] = c;
      if (solver(b, x, y+1)) return true;
    }
    b[x][y] = '.';
    return false;
  }

  HashSet<Character> getAvailable(char[][] b, int x, int y){
    HashSet<Character> set= new HashSet<Character>(Arrays.asList(new Character[]{'1', '2','3','4','5','6','7','8','9'}));
    for (int i=0; i< 9; i++){
      set.remove(b[x][i]);
      set.remove(b[i][y]);
    }
    int r = x / 3 * 3;
    int c = y / 3 * 3;
    for(int i=0; i<3; i++){
      for(int j=0; j<3; j++){
        set.remove(b[r+i][c+j]);
      }
    }
    return set;
  }
}
