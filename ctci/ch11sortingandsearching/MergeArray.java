package ch11sortingandsearching;

/*
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order.
 */

import java.util.Arrays;

/*
 * work from back to front, sort from large to small
 */
public class MergeArray {
  static void mergeArray(int[] A, int[] B, int bufferSize) {
    int idxA = A.length - bufferSize - 1;
    int idxB = B.length - 1;
    int idx = A.length - 1;
    while(idxA >= 0 && idxB >= 0) {
      if (A[idxA] > B[idxB]) {
        A[idx--] = A[idxA--];
      } else {
        A[idx--] = B[idxB--];
      }
    }
    if (idxA < 0) { // if condition is not necessary
      while(idxB >=0) A[idx--] = B[idxB--];
    }
    if (idxB < 0) { // don't need if
      while(idxA >= 0) A[idx--] = A[idxA--];
    }
  }

  public static void main(String[] args) {
    int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
    int[] b = {1, 4, 5, 6, 22, 37};
    mergeArray(a, b, 6);
    System.out.println(Arrays.toString(a));
  }
}
