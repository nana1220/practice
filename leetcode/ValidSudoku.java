/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class Solution {
  public boolean isValidSudoku(char[][] board) {
    for (int i =0; i<9; i++){
      HashSet<Character> set = new HashSet<Character>();
      for (int j=0; j<9; j++){
        if(board[i][j] != '.'){
          if(set.contains(board[i][j])) return false;
          else set.add(board[i][j]);
        }
      }
    }
    for (int j =0; j<9; j++){
      HashSet<Character> set = new HashSet<Character>();
      for (int i=0; i<9; i++){
        if(board[i][j] != '.') {
          if(set.contains(board[i][j])) return false;
          else set.add(board[i][j]);
        }
      }
    }
    for (int i=0; i<9; i += 3) { // use top left corner to locate 3*3 sub-square
      for (int j=0; j<9; j+=3){
        HashSet<Character> set = new HashSet<Character>();
        for(int r =0; r<3; r++){
          for(int c=0; c<3; c++ ){
            if (board[i+r][j+c] != '.') {
              if(set.contains(board[i+r][j+c])) return false;
              else set.add(board[i+r][j+c]);
            }
          }
        }
      }
    }
    return true;
  }
}