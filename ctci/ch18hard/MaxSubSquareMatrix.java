/*
 * Imagine you have a square matrix, where each cell is either black or white. Design an
 * algorithm to find the maximum subsquare such that all four borders are filled with black
 * pixels
 */

package ch18hard;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSubSquareMatrix {

  static class Subsquare { // top left point and size determine a subsquare
    int row;
    int col;
    int size;

    public Subsquare(int row, int col, int size) {
      this.row = row;
      this.col = col;
      this.size = size;
    }

    public String toString() {
      return "row: " + row + " col: " + col + " size: " + size;
    }
  }

  /*
   * brute force O(n^4)
   * locate submatrix by its top left point
   */
  static Subsquare checkSquaresM(int[][] matrix, int m) { // check all size = m squares
    for (int i = 0; i < matrix.length - m + 1; i++) { // the coordinate of sub-square’s top left point (i, j)
      for (int j = 0; j < matrix.length - m + 1; j++) {
        if (checkBoards(matrix, i, j, m)) return new Subsquare(i, j, m);
      }
    }
    return null;
  }

  // row and col is position of submatrix's top left point
  static boolean checkBoards(int[][] matrix, int row, int col, int size) {
    for (int j = col; j < col + size ; j++) {
      if (matrix[row][j] != 1 || matrix[row + size - 1][j] != 1) return false;
    }
    for (int i = row; i < row + size; i++) {
      if (matrix[i][col] !=1 || matrix[i][col + size - 1] != 1) return false;
    }
    return true;
  }

  static Subsquare findMaxSubsquare(int[][] matrix) {
    for (int size = matrix.length; size > 0; size--) {
      Subsquare sub = checkSquaresM(matrix, size);
      if (sub != null) {
        return sub;
      }
    }
    return null;
  }

  /*
   * TODO:
   * Optimize
   * Pre-process the subsquare to reduce the time of checking valid subsqaure from O(n) to O(1)
   * processed[i][j] -- SquareCell(i,j), a wrapper class storing number of continuous black cells on
   * right and below
   * time: O(n^3)
   */

  public static void main(String[] args) {
    int[][] square = new int[][] { { 0, 1, 1, 1}, { 1, 1, 0, 1 }, { 1, 0, 1, 1 },
        { 1, 1, 1, 1 }, };
    System.out.println(findMaxSubsquare(square).toString());
  }
}