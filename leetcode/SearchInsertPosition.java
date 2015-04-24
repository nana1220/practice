/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
 */

/*
start = end = mid：
a. target < A[mid]，则我们知道p = mid。
binary search下一步会搜索A[start = mid : end = mid-1]而终止，在这一步应该返回start。
b. 反之target > A[mid]，则我们知道p = mid+1。
binary search下一步会搜索A[start = mid+1 : end = mid]而终止，这一步我们仍旧应该返回start。
 */
public class Solution {
  public int searchInsert(int[] A, int target) {
    int l =0;
    int r= A.length - 1;
    while(l<=r){
      int m = (l+r)/2;
      if (A[m]==target) return m;
      else if (A[m] < target) l = m+1;
      else r = m -1;
    }
    return l;
  }
}