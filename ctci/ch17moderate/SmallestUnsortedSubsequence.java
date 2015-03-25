/*
Given an array of integers, write a method to find indices m and n such that if you
sorted elements m through n, the entire array would be sorted. Minimize n - m
(that is, find the smallest such sequence)
 */

package ch17moderate;

import java.lang.System;

/*
 * find the longest start and end sorted subsequence, then find the min and max values fo
 * middle part, find the first position that is bigger than min in start subsequence,
 * and find the last position that is smaller or equal to max in end subsequence
 * time: O(n)
 */
public class SmallestUnsortedSubsequence {
  static void findIndexes1(int[] arr) {
    int start = arr.length - 1;
    for (int i = 0; i < arr.length - 1; i++) { // while loop is clearer
      if (arr[i] > arr[i + 1]) {
        start = i;
        break;
      }
    }
    int end = 0;
    for (int i = arr.length - 1; i > 0; i--) { // while loop is clearer
      if (arr[i] < arr[i - 1]) {
        end = i;
        break;
      }
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = start; i < end + 1; i++) {
      if (arr[i] < min) min = arr[i];
      if (arr[i] > max) max = arr[i];
    }
    for (int i = 0; i < start; i++) {
      if (arr[i] > min) {
        start = i;
        break;
      }
    }
    for (int i = end + 1; i < arr.length; i++) {
      if (arr[i] > max) {
        end = i - 1;
        break;
      }
    }
    System.out.println("start:" + start + "end:" + end);
  }

  /*
   * use insertion sort
   * time: O(n^2)
   */
  static void findIndexes2(int[] arr) {
    int start = Integer.MAX_VALUE;
    int end = Integer.MIN_VALUE;
    for (int j = 1; j < arr.length; j++) {
      int val = arr[j];
      for (int i = 0; i < j; i++) {
        if (arr[i] > arr[j]) {
          if (i < start) start = i;
          if (j > end) end = j;
          System.arraycopy(arr, i, arr, i + 1, j - i);
          arr[i] = val;
        }
      }
    }
    System.out.println("start:" + start + "end:" + end);
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 4, 7, 10, 11, 8, 12, 5, 7, 16, 18, 19};
    findIndexes1(array);
    findIndexes2(array);
  }
}