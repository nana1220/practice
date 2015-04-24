/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
 */

public class Solution {
  // TLE, O(2^n)  at each index, can break or not break, that's 2^n
  // TODO: write a dfs that returns ArrayList<String>, then can use top-down dp cache result
  public ArrayList<String> wordBreak(String s, Set<String> dict) {
    ArrayList<String> res = new ArrayList<String>();
    ArrayList<String> list = new ArrayList<String>();
    wordBreak(s, dict, res, list);
    return res;
  }

  void wordBreak(String s, Set<String> dict, ArrayList<String> res, ArrayList<String> list) {

    if (s.length() == 0) {
      StringBuilder sb = new StringBuilder();
      for (String str : list) sb.append(str + " ");
      sb.deleteCharAt(sb.length() - 1);
      res.add(sb.toString());
      return;
    }
    for (int i = 1; i <= s.length(); i++) {
      if (dict.contains(s.substring(0, i))) {
        list.add(s.substring(0, i));
        wordBreak(s.substring(i), dict, res, list);
        list.remove(list.size() - 1);
      }
    }
  }
}

// first store result in dp, then use dfs build result path
// DP + DFS, dp[0]: initialized value, dp[i] store words that end at (i-1)-th index of string s
public class Solution {
  public List<String> wordBreak(String s, Set<String> dict) {
    //create an array of ArrayList<String>
    List<String>[] dp = new ArrayList[s.length() + 1]; // can also use a hashmap with array index as key
    dp[0] = new ArrayList<String>();

    for (int i = 0; i < s.length(); i++) {
      if (dp[i] == null)
        continue;

      for (String word : dict) {
        int len = word.length();
        int end = i + len;
        if (end > s.length())
          continue;

        if (s.substring(i, end).equals(word)) {
          if (dp[end] == null) {
            dp[end] = new ArrayList<String>();
          }
          dp[end].add(word); // store all valid words according to word's end index
        }
      }
    }

    List<String> result = new LinkedList<String>();
    if (dp[s.length()] == null)
      return result;

    ArrayList<String> temp = new ArrayList<String>();
    dfs(dp, s.length(), result, temp);

    return result;
  }

  // dfs build result, traverse from end back to start
  public void dfs(List<String> dp[], int end, List<String> result, ArrayList<String> tmp) {
    if (end <= 0) {
      String path = tmp.get(tmp.size() - 1);
      for (int i = tmp.size() - 2; i >= 0; i--) {
        path += " " + tmp.get(i);
      }
      result.add(path);
      return;
    }

    for (String str : dp[end]) {
      tmp.add(str);
      dfs(dp, end - str.length(), result, tmp);
      tmp.remove(tmp.size() - 1);
    }
  }
}