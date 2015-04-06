package ch11sortingandsearching;

/*
 * Given an M * N matrix in which each row and each column is sorted in ascending
 * order, write a method to find an element.
 */

/*
do binary search on every row to find the element. This
algorithm will be 0(M log(N)), since there are M rows and it takes 0(log(N)) time
to search each one.
 */

import java.lang.System;
import java.util.Arrays;

public class SearchMatrix {
  /*
   * either starts from top right or bottom left, these two points are mid points of the matrix,
   * that is either starts from smallest row but largest col or from largest row but smallest col
   * time: O(m+n)
   */
  public static int[] search1(int[][] matrix, int x, int rowStart, int colEnd) {
    while(rowStart < matrix.length && colEnd > -1) {
      if (matrix[rowStart][colEnd] == x) return new int[]{rowStart, colEnd};
      if (matrix[rowStart][colEnd] > x) colEnd--; // colEnd gets to -1
//      if (matrix[rowStart][colEnd] < x) rowStart++; // but still evaluate colEnd here, will make index out of bound
      else rowStart++; // Note!!: update either row or col for each loop, don't update both
    }
    return new int[]{-1, -1};
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][] {
        { 15, 20, 70, 85 },
        { 20, 35, 80, 95 },
        { 30, 55, 95, 105 },
        { 40, 80, 120, 120 }
    };

    int[] res = search1(matrix, 55, 0, 3);
    System.out.println(Arrays.toString(res));
    res = search1(matrix, 22, 0, 3);
    System.out.println(Arrays.toString(res));
  }
}