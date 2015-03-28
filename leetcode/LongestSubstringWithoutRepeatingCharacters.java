/*
Given a string, find the length of the longest substring without repeating characters. For example,
the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
For "bbbbb" the longest substring is "b", with the length of 1.
 */

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

  /*
   * maintain head and end of substring
   * use map to cache characters and their most recent positions
   *
   * time: O(n^2)
   * get method is O(1) on average but in worst case it is O(n)
   */
  public int lengthOfLongestSubstring1(String s) {
    int max = 0;
    int head = 0;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for (int end = 0; end < s.length(); end++) {
      char ch = s.charAt(end);
      Integer idx = map.get(ch);
      if (idx == null) map.put(ch, end);
      else {
        // NOTE: if the duplicated word appears before head of current substring, no need to update head
        head = Math.max(head, idx + 1);
        map.put(ch, end);
      }
      // NOTE: need to update max every iteration, otherwise size of current substring will not return
      // only previous longest substring is returned
      max = Math.max(max, end - head + 1);
    }
    return max;
  }

  /*
   * use an array marker instead of map, the value of element in the marker the char's position
   * time: O(n)
   */
  public int lengthOfLongestSubstring2(String s) {
    int max = 0;
    int marker[] = new int[256]; // UTF-8
    Arrays.fill(marker, -1);
    int head = 0;
    for (int end = 0; end < s.length(); end++) {
      char ch = s.charAt(end);
      int chIdx = marker[ch];
      if (chIdx == -1) marker[ch] = end;
      else {
        head = Math.max(head, chIdx + 1);
        marker[ch] = end;
      }
      max = Math.max(max, end - head + 1);
    }
    return max;
  }
}