/*
  Say you have an array for which the ith element is the price of a given stock on day i.
  Design an algorithm to find the maximum profit. You may complete at most two transactions.
  Note:
  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

// Similar to Leetcode/TrappingRainWater
// NOTE::!!! this is a good trick sum[i] = left[i] + right[i].
// time: O(n); space: O(n)
public class Solution {
  public int maxProfit(int[] prices) {
    if (prices==null || prices.length==0)   return 0;
    int m=prices.length;
    int[] left = new int[m]; // max profit on interval [0,i], left[0]=0
    int[] right = new int[m]; // max profit on interval [i,m-1] right[m-1]=0
    int lmin=prices[0], rmax=prices[m-1], sum=0;
    for (int i=1; i<prices.length; i++){
      left[i] = Math.max(left[i-1], prices[i]-lmin); // from left to right, update min value (buy price)
      lmin = Math.min(lmin, prices[i]);
    }
    for (int i=m-2; i>=0; i--){
      right[i] = Math.max(right[i+1], rmax-prices[i]); // from right to left, update max value (sell price)
      rmax = Math.max(rmax, prices[i]);
    }
    for (int i=0; i<m; i++){
      sum = Math.max(sum, left[i] + right[i]);
    }
    return sum;
  }
}