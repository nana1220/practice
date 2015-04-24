/*
Given a string S, find the length of the longest substring T that contains at most two
distinct characters.
For example,
Given S = “eceba”,
T is "ece" which its length is 3.
 */

// sliding window
// maintain three pointers, i left end of window, [i,j] window for first word, k right end of sliding window
/*
1）在最后返回的时候，注意考虑s.length()-i这种情况，也就是字符串读取到最后而没有触发（1.a）
2）讲解清楚sliding window的更新
3）该题目有个follow-up，就是如果是k个distinct characters怎么办。这样的话就只能对所有可能的字符用一个数组去做counting，而且只能假设ASIC字符集256
 */
public class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int i = 0, j = -1, maxLen = 0;
    for (int k = 1; k < s.length(); k++) {
      if (s.charAt(k) == s.charAt(k - 1)) continue;
      if (j >= 0 && s.charAt(j) != s.charAt(k)) {
        maxLen = Math.max(k - i, maxLen);
        i = j + 1;
      }
      j = k - 1;
    }
    return Math.max(s.length() - i, maxLen);
  }
}