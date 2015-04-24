/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


 */
// find the highest left bar and highest right bar for each position
// final bar for each position will be the samller one of the two
public class Solution {
  public int trap(List<Integer> height) {
    int len = height.size();
    int[] lmax = new int[len]; // an array for the position's left max at each position, most left has leftmax 0
    int[] rmax = new int[len]; // // an array for the position's right max at each position, most right has rightmax 0
    int max=0;
    for(int i=len-1; i>=0; i--){
      rmax[i]=max;
      max=Math.max(max, height.get(i));
    }
    max=0;
    for(int i=0; i<len; i++){
      lmax[i]=max;
      max=Math.max(max,height.get(i));
    }
    int res=0;
    for(int i=1; i<len-1; i++){
      int bar = Math.min(lmax[i], rmax[i]);
      if(bar > height.get(i)) { //NOTE: don't forget to check!!!
        res += bar-height.get(i);
      }
    }
    return res;
  }
}