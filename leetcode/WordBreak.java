/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */

// DFS+ dp, top-down
public class Solution {
  public boolean wordBreak(String s, Set<String> dict) {
    HashMap<String, Boolean> dp = new HashMap<String, Boolean>();
    return wordBreak(s, dict, dp);
  }

  boolean wordBreak(String s, Set<String> dict, HashMap<String, Boolean> dp) {
    if (dp.containsKey(s)) return dp.get(s);
    if (s.length() == 0) return true;
    for (int i = 1; i <= s.length(); i++) { // NOTE!!:  <= not just <, so that left can be whole string
      if (dict.contains(s.substring(0, i))) {
        if (wordBreak(s.substring(i), dict, dp)) {
          dp.put(s, true);
          return true;
        }
      }
    }
    dp.put(s, false);
    return false;
  }
}

/*
 DP: bottom up
Define an array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
Initial state t[0] == true
Time: O(string length * dict size)
*/
public class Solution {
  public boolean wordBreak(String s, Set<String> dict) {
    boolean[] t = new boolean[s.length() + 1];
    t[0] = true; //set first to be true, why?
    //Because we need initial state

    for (int i = 0; i < s.length(); i++) {
      //should continue from match position
      if (!t[i])
        continue;

      for (String a : dict) {
        int len = a.length();
        int end = i + len;
        if (end > s.length())
          continue;

        if (t[end]) continue;

        if (s.substring(i, end).equals(a)) {
          t[end] = true;
        }
      }
    }
    return t[s.length()];
  }
}
