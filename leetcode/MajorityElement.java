/*
Given an array of size n, find the majority element. The majority element is the element that
appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

// O(n), linear scan
public class Solution {
  public int majorityElement(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i : nums){
      Integer count = map.get(i);
      if(count==null) map.put(i,1);
      else map.put(i, count+1);
    }
    for(int key : map.keySet()){
      if(map.get(key) > nums.length /2) return key;
    }
    return -1;
  }
}



// sort the array first, which takes time of nlog(n). Then scan once to find the
// longest consecutive substrings.

public class Solution {
  public int majorityElement(int[] num) {
    if (num.length == 1) {
      return num[0];
    }
    Arrays.sort(num);
    int prev = num[0];
    int count = 1;
    for (int i = 1; i < num.length; i++) {
      if (num[i] == prev) {
        count++;
        if (count > num.length / 2) return num[i];
      } else {
        count = 1;
        prev = num[i];
      }
    }
    return 0;
  }

  // Since the majority always take more than a half space, the middle element is guaranteed to be
  // the majority. Sorting array takes nlog(n). So the time complexity of this solution is nlog(n).
  public int majorityElement(int[] num) {
    if (num.length == 1) {
      return num[0];
    }
    Arrays.sort(num);
    return num[num.length / 2];
  }
}