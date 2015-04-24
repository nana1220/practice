/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */

import java.lang.System;

// brute force, TLE and wrong
public class JumpGameII {
  static int mincount = Integer.MAX_VALUE;
  static public int jump(int[] A) {
    jump(A, 0, 0);
    return mincount;
  }
  static void jump(int[] A, int idx, int count){
    if (idx >= A.length) return;
    if(A[idx] >= (A.length-1-idx) ) {
      mincount = Math.min(mincount, ++count);
      return;
    }
    for (int i =1; i <= A[idx]; i++){
      jump(A, idx+i, ++count);
    }
  }

  // greedy algo
  // Maintain two reaches, one is the previous max reach, another is the current max reach
// When we run out of the previous max reach, it's time to add one more step
// time: O(n); space: O(1)
  static public int jump2(int[] A) {
      if (A==null || A.length==0)
        return 0;
      int N = A.length, step = 0, prevMax = 0, currMax = 0;
      for (int i=0; i<N; i++){
        if (i>prevMax){
          prevMax = currMax;
          step++;
        }
        currMax = Math.max(currMax, i+A[i]);
      }
      return step;
    }


  public static void main(String[] args) {
    System.out.println(jump(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
    System.out.println(jump2(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
  }
}
