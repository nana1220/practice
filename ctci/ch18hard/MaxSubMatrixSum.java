/*
 * Given an N*N matrix of positive and negative integers, write code to find the sub-matrix with
 * the largest possible sum.
 */

package ch18hard;

public class MaxSubMatrixSum {
  /*
   * brute force
   * sub-matrix not just sub-square-matrix, top-left point + size is not enought
   * Need two diagonal points to represent a submatrix, there're n^2 points, thus (n^2)^2=n^4
   * submatrix,
   * iterate through O(n^4) sub-matrices and it takes O(n^2) to compute the area of each
   * time: O(n^6); space: O(1), space is about the extra memory space besides algorithm input
   */
  static int bruteForceSum(int[][] matrix) {
    int max = Integer.MIN_VALUE;
    for (int row1 = 0; row1 < matrix.length; row1++) {
      for (int col1 = 0; col1 < matrix[0].length; col1++) {
        // NOTE: row2 start with row1 instead of row1 + 1, so does col2 start with col1
        // which allow 1 * 1 matrix
        for (int row2 = row1; row2 < matrix.length; row2++) {
          for (int col2 = col1; col2 < matrix[0].length; col2++) {
            int val = sum(matrix, row1, row2, col1, col2);
            max = Math.max(val, max);
          }
        }
      }
    }
    return max;
  }

  static int sum(int[][] matrix, int row1, int row2, int col1, int col2) {
    int sum = 0;
    for (int i = row1; i < row2 + 1; i++) {
      for (int j = col1; j < col2 + 1 ; j++) {
        sum += matrix[i][j];
      }
    }
    return sum;
  }

  /*
   * DP: pre-processing
   * Pre-compute the area of submatrix [(0, 0), (i, j)] for each point (i, j) this takes O(n^2) time
   * reduce the computation time of sub-matrix sum from O(n^2) to O(1).
   * The sum of submatrix [(i1, j1), (i2, j2)] is [(0, 0), (i2, j2)] + [(0, 0), (i1 - 1, j1 - 1)]
   * - [(0, 0), (i1 - 1, j2)] - [(0, 0), (i2, j1 - 1)]
   *
   */
  static int precomputeDP(int[][] matrix) {
    int[][] sumMatrix = precompute(matrix);
    int max = Integer.MIN_VALUE;
    for (int row1 = 0; row1 < matrix.length; row1++) {
      for (int col1 = 0; col1 < matrix[0].length; col1++) {
        for (int row2 = row1; row2 < matrix.length; row2++) {
          for (int col2 = col1; col2 < matrix[0].length; col2++) {
            //NOTE:!!!!! MUST CONSIDER EDGE CASE
//            int val = sumMatrix[row2][col2] + sumMatrix[row1 - 1][col1 - 1]
//                - sumMatrix[row1 - 1][col2] - sumMatrix[row2][col1 - 1];
            int val = computeSum(sumMatrix, row1, col1, row2, col2);
            max = Math.max(val, max);
          }
        }
      }
    }
    return max;
  }

  /*
   * edge case!!!!
   */
  static int computeSum(int[][] sumMatrix, int row1, int col1, int row2, int col2) {
    if (row1 == 0 && col1 == 0) return sumMatrix[row2][col2];
    if (row1 == 0) return sumMatrix[row2][col2] - sumMatrix[row2][col1 - 1];
    if (col1 == 0) return sumMatrix[row2][col2] - sumMatrix[row1 - 1][col2];
    return sumMatrix[row2][col2] + sumMatrix[row1 - 1][col1 - 1]
        - sumMatrix[row1 - 1][col2] - sumMatrix[row2][col1 - 1];
  }

  /*
   * compute and store sum of [(0, 0), (i, j)] in (i,j)
   * NOTE: different cases in the loop!!!
   */
  static int[][] precompute(int[][] matrix) {
    int[][] sumMatrix = new int[matrix.length][matrix[0].length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        if (row == 0 && col == 0) sumMatrix[0][0] = matrix[0][0];
        else if (row == 0) sumMatrix[0][col] = sumMatrix[0][col - 1] + matrix[0][col];
        else if (col == 0) sumMatrix[row][0] = sumMatrix[row - 1][0] + matrix[row][0];
        else sumMatrix[row][col] = sumMatrix[row - 1][col] + sumMatrix[row][col - 1]
            - sumMatrix[row - 1][col - 1] + matrix[row][col];
      }
    }
    return sumMatrix;
  }

/*
 * TODO
 * optimized: apply max sum of subarray
 * Maintain the two pointers to mark the top and bottom border of the submatrix
 * For each submatrix, get the sum of each column, and get MaxSubArray of the columnSum array
 * time: O(n^3); space: O(n)
 */


  public static void main(String[] args) {
    int[][] matrix = new int[][] { { 1, 1, 1, -1 }, { 1, 2, 1, -1 }, { 1, 1, 1, 1 },
        { -1, -1, -1, 1 }, };
    System.out.println(bruteForceSum(matrix));
    System.out.println(precomputeDP(matrix));
  }

}
