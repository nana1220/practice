/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 */

public class Solution {
  public int search(int[] A, int target) {
    return search(A, target, 0, A.length -1);
  }

  int search(int[] A, int target, int l, int r) {
    if (l > r) return -1;
    int mid = (l + r)/2;
    if (target == A[mid]) return mid;
    if (A[l] <= A[mid]) {
      if (target < A[mid]) {
        int res = search(A, target, l, mid - 1);
        if (res != -1) return res;
        else return search(A, target, mid+1, r);
      }
      if (target > A[mid]) return search(A, target, mid+1, r);
    }
    else {
      if (target < A[mid]) {
        return search(A, target, l, mid - 1);
      } else {
        int res = search(A, target, mid+1, r);
        if (res != -1) return res;
        else return search(A, target, l, mid -1);
      }
    }
    return -1;
  }
}