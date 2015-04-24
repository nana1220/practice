/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 */
public class Solution {
  public int longestConsecutive(int[] num) {
    Set<Integer> set = new HashSet<Integer>();
    for(int n : num) set.add(n);
    int maxLen = 1;
    while(!set.isEmpty()){
      int val = set.iterator().next();
      set.remove(val);
      int len =1;
      int copy = val;
      while(true){
        copy++;
        if (set.contains(copy)) {
          len++;
          set.remove(copy);
        } else break;
      }
      while (true) {
        val--;
        if (set.contains(val)) {
          len++;
          set.remove(val);
        } else break;
      }
      maxLen = Math.max(maxLen, len);
    }
    return maxLen;
  }
}