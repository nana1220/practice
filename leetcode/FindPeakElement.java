/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
 */

/*
因为num[-1] = num[n] = -∞，所以只要数组中没有连续出现的相同元素，这个peak element就总是存在的。
二分查找所依据的关键，就是如果num[mid]<=num[mid-1]，那么peak element一定存在于区间[l, mid-1]；如果num[mid]<=num[mid+1]，那么peak element一定存在于区间[mid+1, r]
 */
public class Solution {
  public int findPeakElement(int[] num) {
    if (num==null) return -1;
    if (num.length==1) return 0;
    int l = 0, r = num.length-1;
    while (l<=r) {
      int mid = (l+r)/2;
      if ( mid==0 ) { //note: when hitting the end, only compare with one side
        if ( num[mid]>num[mid+1] ) return mid;
        else l = mid+1;
      }
      else if ( mid==num.length-1 ) {
        if ( num[mid]>num[mid-1] ) return mid;
        else r = mid-1;
      }
      else if ( num[mid]>num[mid-1] && num[mid]>num[mid+1] ) { // not hitting the end, compare with both side
        return mid;
      }
      else if ( num[mid]<num[mid-1]) { // BST
        r = mid -1;
      }
      else { // num[mid]>num[mid-1]
        l = mid + 1;
      }
    }
    return -1;
  }
}