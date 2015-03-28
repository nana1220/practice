/*
Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
are not zero-based.You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 */

import java.util.HashMap;

/*
 *  use map, one pass through the array, add value and index to map, check map.containsKey(target - currentValue),
 *  no need to consider a number plus itself equal to target, since only one pass
 *
 *  time: O(n)
 */
public class TwoSum {
  public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < numbers.length; i++) {
      if (!map.containsKey(target - numbers[i])) {
        map.put(numbers[i], i);
      }
      else {
        res[0] = map.get(target - numbers[i]) + 1;
        res[1] = i + 1;
        return res;
      }
    }
    return null;
  }
}


