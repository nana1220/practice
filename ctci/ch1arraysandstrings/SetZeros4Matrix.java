package ch1arraysandstrings;

import java.lang.String;
import java.lang.System;

public class SetZeros4Matrix {
  public static void main(String[] args) {
    int[][] matrix = new int[][] {
        {1, 2, 3, 4, 6},
        {5, 0, 7, 8, 15},
        {9, 10, 11, 12, 17},
        {13, 14, 0, 16, 18}
    };
    setZeros(matrix);
    printMatrix(matrix);
  }

  /*
   * to make it more space efficient, instead of using boolean array
   * use bit vector (e.g. an int for a 32 bit vector, so it can handle at most 32 * 32 matrix)
   */
  static void setZeros(int[][] matrix) { // M * N matrix
    // mark the row number and col number that need to be set zeros
    int M = matrix.length;
    int N = matrix[0].length;
    boolean[] row = new boolean[M];
    boolean[] col = new boolean[N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] == 0) {
          row[i] = true;
          col[j] = true;
        }
      }
    }
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (row[i] || col[j]) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  static void printMatrix(int[][] matrix) {
    for (int[] val : matrix) {
      for (int v : val) {
        System.out.print(v + " ");
      }
      System.out.print("\n");
    }
  }
}