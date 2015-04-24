/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
 */
public class Solution {
  public boolean search(int[] A, int target) {
    int l=0;
    int r=A.length-1;
    while(l<=r){
      int m=(l+r)/2;
      if(A[m]==target) return true;
      if(A[l] < A[m]){ // left half in order
        if(A[l]<= target && target < A[m]) r=m-1; // note <= not just <, to cover all left half
        else l=m+1;
      }
      else if(A[m]<A[l]){ // right half in order
        if(A[m]<target&& target<=A[r]) l=m+1; // <= to cover all right half
        else r=m-1;
      } else {
        if(A[l] == target) return true;
        else l++;
      }
    }
    return false;
  }
}