/*
Implement int sqrt(int x).

Compute and return the square root of x.
 */

// binary search,
public class Solution {
  public int mySqrt(int x) {
    if(x<=1) return x;
    int start = 0;
    int end = x;
    while(start <= end) {
      int mid = start + (end-start)/2; // better than (start+end)/2 because the latter tends to overflow
      if(mid==x /mid) return mid; // NOTE!!!!!!!!: dont use mid*mid, will overflow in many cases
      if(mid<x/mid) {
        start = mid+1;
      } else {
        end = mid -1;
      }
    }
    return end; // return smaller
  }
}
