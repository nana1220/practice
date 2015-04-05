/*
Given a string S and a string T, find the minimum window in S which will contain all the
characters in T in complexity O(n).
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such windows, you are guaranteed that there will always be only one unique
minimum window in S.
*/

// Sliding Window
// Maintain two arrays to record the occurrences of needed characters and found characters
// Traverse the String S, update the occurrence arrays and the number of remaining chars that need to be found.
// When all needed chars haven been found, right shift the start index of window if possible,
// and update the min window.
// time: O(n); space: O(n), each char is accessed at most twice

public class MinimumWindowSubstring {
  public static String minWindow(String S, String T) {
    if (S == null || S.length() == 0 || T == null || T.length() == 0) return "";
    HashMap<Character, Integer> target = new HashMap<Character, Integer>(); // NOTE: can instead use int[256]
    HashMap<Character, Integer> found = new HashMap<Character, Integer>();

    for (char c : T.toCharArray()) {
      found.put(c, 0);
      if (!target.containsKey(c)) target.put(c, 1);
      else target.put(c, target.get(c) +1);
    }
    int start = 0;
    int count = 0;
    String res = "";
    for (int end = 0; end < S.length(); end++) {
      char ch = S.charAt(end);
      if (target.containsKey(ch)) {
        if (found.get(ch) < target.get(ch)) count++;
        found.put(ch, found.get(ch) + 1);
      }
      if (count == T.length()) {
        while (!target.containsKey(S.charAt(start)) || found.get(S.charAt(start)) > target.get(S.charAt(start))) {
          if(found.containsKey(S.charAt(start))) found.put(S.charAt(start), found.get(S.charAt(start)) - 1);
          start++;
        }
        if (res.equals("") || end - start + 1 < res.length()) res = S.substring(start, end + 1);
        found.put(S.charAt(start), found.get(S.charAt(start)) - 1); // remove the first character of the window
        start++; // in order to find next valid window
        count--; // first character must be valid, so count--
      }
    }
    return res;
  }
}
