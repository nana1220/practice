package ch17moderate;

import java.util.HashMap;

public class TicTacToe {


  static HashMap<Integer, Boolean> hasWonTable;

  static enum Cell {
    EMPTY(0), BLACK(1), WHITE(2);
    private int val; // both a field and a private constructor are necessary to make enum items have
    private Cell(int v) { // their corresponding values
      val = v;
    }

    public int value() { // so corresponding value can be accessed
      return val;
    }
  }

  /*
   * convert each board to an integer, lookup hash table to see if this board has won or not,
   * lookup time very efficient
   */
  static boolean hasWon1(Cell[][] board) {
    int boardNumber = 0;
    int factor;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        factor = i * 3 + j; // set initial factor = 1, that is 3^0, then factor *= 3 for each loop
        boardNumber += board[i][j].value() * (int) Math.pow(3, factor);
      }
    }
    return hasWonTable.get(boardNumber);
  }

  /*
      * check 3 * 3 board
     */
  static Cell hasWon2(Cell[][] board) {
    for (int i = 0; i < 3; i++) {
      // check row
      if (board[i][0] != Cell.EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
        return board[i][0];
      }
      // check col
      if (board[0][i] != Cell.EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
        return board[0][i];
      }
    }
    // check diagonal
    if (board[0][0] != Cell.EMPTY) {
      for (int i = 1; i < 3; i++) {
        if (board[0][0] != board[i][i]) {
          break;
        }
        if (i == 2) return board[0][0];
      }
    }
    // check reverse diagonal
    if (board[0][2] != Cell.EMPTY) {
      for (int i = 1; i < 3; i++) {
        if (board[0][2] != board[i][2 - i]) {
          break;
        }
        if (i == 2) return board[0][2];
      }
    }
    return Cell.EMPTY;
  }
}