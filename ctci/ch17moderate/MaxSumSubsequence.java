/*
 You are given an array of integers (both positive and negative). Find the contiguous
 sequence with the largest sum. Return the sum.
 */

package ch17moderate;

public class MaxSumSubsequence {

  // brute force
  // time: O(n^3)
  static void sum(int[] arr, int[] sums, int index) {
    if (arr.length - 1 == index) {
      sums[index] = arr[index];
      return;
    }
    int maxSum = Integer.MIN_VALUE;
    for(int i = index + 1; i <= arr.length; i++) {
      int sum = 0;
      for(int j = index; j < i; j++) {
        sum += arr[j];
      }
      if (sum > maxSum) maxSum = sum;
    }
    sums[index] = maxSum;
    sum(arr, sums, index + 1);
  }

  static int maxSum1(int[] arr) {
    int[] sums = new int[arr.length];
    sum(arr, sums, 0);
    int maxSum = Integer.MIN_VALUE;
    for(int s : sums) if (s > maxSum) maxSum = s;
    return maxSum;
  }

  // time O(n)
  static int maxSum2(int[] arr) {
    int maxSum = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      if(sum > maxSum) {
        maxSum = sum; // Note!!: check for each iteration
      }
      // whenever sum becomes negative, the left part is useless, reset sum to 0 and start with next value
      if ((sum + arr[i]) <= 0) {
        sum = 0;
      } else {
        sum += arr[i]; // Note: every time sum gets updated, compare to see if need to set maxSum to new value
      }
    }
    return maxSum;
  }
  public static void main(String[] args) {
    int[] a = {2, 3, -8, -1, 2, 4, -2, -3};
    System.out.println(maxSum1(a));
    System.out.println(maxSum2(a));
  }
}