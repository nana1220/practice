/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given
number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

public class ThreeSumClosest {
  /*
   * O(n^2), run an outer loop for first element, and maintain two pointers do two sum
   */
  public int threeSumClosest(int[] num, int target) {
    Arrays.sort(num); // Don't forget sort first
    int error = Integer.MAX_VALUE;
    int res = target;
    for(int i = 0; i < num.length; i++){
      int sum = target - num[i];
      int l = i + 1;
      int r = num.length - 1;
      while (l < r) {
        int err = num[l] + num[r] - sum;
        if(Math.abs(err) < error) {
          res = num[l] + num[r] + num[i];
          error = Math.abs(err);
        }
        if (err > 0) r--;
        else l++;
      }
    }
    return res;
  }
}

