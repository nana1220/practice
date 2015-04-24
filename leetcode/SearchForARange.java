/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 */


public class Solution {
  // worst case O(n) not O(log(n))
  public int[] searchRange(int[] A, int target) {
    int l = 0;
    int r = A.length - 1;
    int m = (l + r) / 2;
    while (l <= r) {
      m = (l + r) / 2;
      if (A[m] == target) break;
      else if (A[m] < target) l = m + 1;
      else r = m - 1;
    }
    if (l > r) {
      int[] res = new int[]{-1, -1};
      return res;
    }
    r = m;
    while (r + 1 < A.length && A[r] == A[r + 1]) r++;
    l = m;
    while (l - 1 >= 0 && A[l - 1] == A[l]) l--;
    int[] res = new int[2];
    res[0] = l;
    res[1] = r;
    return res;
  }

  // two BST, find left bound and right bound respectively
  private int getLowerBound(int[] A, int target) {
    int start = 0, end = A.length - 1;
    while (start <= end) {
      int mid = (start + end) >> 1;
      if (A[mid] >= target) end = mid - 1;
      else start = mid + 1;
    }
    return end + 1; // why? because end will decrement until A[mid]<target, which is one position left off
  }

  private int getUpperBound(int[] A, int target) {
    int start = 0, end = A.length - 1;
    while (start <= end) {
      int mid = (start + end) >> 1;
      if (A[mid] > target) end = mid - 1;
      else start = mid + 1;
    }
    return start - 1;
  }
  public int[] searchRange(int[] A, int target) {
    int[] res = new int[]{-1,-1};
    if (A == null || A.length == 0) return res;
    int lower = getLowerBound(A, target);
    int upper = getUpperBound(A, target);
    if (lower>upper)    return res;     // Key step here. Remember to check the case that target is not in the array
    res[0] = lower;
    res[1] = upper;
    return res;
  }
}