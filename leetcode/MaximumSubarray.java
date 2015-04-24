/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */

public class Solution {
  public int maxSubArray(int[] A) {
    int maxSum= Integer.MIN_VALUE;
    int sum=0;
    for(int i=0; i<A.length;i++){
      sum+=A[i];
      maxSum=Math.max(sum, maxSum);
      if(sum<0) sum=0;
    }
    return maxSum;
  }
}
// DP
public class Solution {
  public int maxSubArray(int[] A) {
    int max = A[0];
    int[] sum = new int[A.length];
    sum[0] = A[0];

    for (int i = 1; i < A.length; i++) {
      sum[i] = Math.max(A[i], sum[i - 1] + A[i]);
      max = Math.max(max, sum[i]);
    }

    return max;
  }
}