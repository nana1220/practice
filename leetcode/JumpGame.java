/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 */


// brute force: TLE
public class Solution {
  public boolean canJump(List<Integer> nums) {
    return canJump(nums,0);
  }
  boolean canJump(List<Integer> nums, int idx){
    if(idx==nums.size()-1) return true;
    boolean res = false;
    for (int i =1; i <= nums.get(idx); i++) {
      if (idx+ i < nums.size())
        res |= canJump(nums, idx+i);
    }
    return res;
  }
}
// dp: TLE
public class Solution {
  public boolean canJump(List<Integer> nums) {
    boolean[] dp = new boolean[nums.size()];
    dp[0] = true;
    for (int i=0; i< nums.size(); i++) {
      if (dp[i]) {
        int step = nums.get(i);
        for(int j=1; j<=step; j++){
          if (i+j<dp.length) {
            dp[i + j] = true;
          }
        }
      }
    }
    return dp[dp.length-1];
  }
}
// maintain a max index that can be reached, if next idx > current maxIdx return false
public class Solution {
  public boolean canJump(List<Integer> nums) {
    int maxIdx=0;
    for(int i=0; i<nums.size()-1; i++){
      maxIdx= Math.max(nums.get(i)+i, maxIdx);
      if((i+1)>maxIdx) return false;
    }
    return true;
  }
}