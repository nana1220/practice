/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell
one share of the stock multiple times). However, you may not engage in multiple transactions at the same time
(ie, you must sell the stock before you buy again).
 */

// sum up heights of all increasing interval
public class Solution {
  public int maxProfit(int[] prices) {
    if(prices.length==0) return 0;
    int res=0;
    int min=prices[0];
    boolean hold=false;
    for(int i=1;i<prices.length;i++){
      if(!hold) {
        if(prices[i]>prices[i-1]) {
          min = prices[i-1];
          hold=true;
        }
      } else{
        if(prices[i]<prices[i-1]) {
          res += prices[i-1] - min;
          hold=false;
          min = prices[i];
        }
      }
    }
    if(hold) res += prices[prices.length-1] -min; // NOTE!!!!!!!!!: if keep holding in the end, need to sell
    return res;
  }
}

// a cleaner implementation
// Greedy, buy stock when we find current price is higher than the previous one
// time: O(n); space: O(1)
public class Solution {
  public int maxProfit(int[] prices) {
    if (prices==null || prices.length==0)   return 0;
    int sum=0;
    for (int i=1; i<prices.length; i++){
      if (prices[i] > prices[i-1])    sum += prices[i]-prices[i-1];
    }
    return sum;
  }
}