/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum
length of S is 1000, and there exists one unique longest palindromic substring.
 */

import java.lang.System;
import java.util.Stack;
public class LongestPalindromicSubstring {

  /*
   * starting from longest length, check if string is palindrome, decrease length until find first one
   * Time Limit Exceeded
   */
  public String longestPalindrome1(String s) {
    for (int length = s.length(); length > 0; length--) {
      for (int i = 0; i < s.length() - length + 1; i++) {
        if (isPalindrome(s.substring(i, i + length))) return s.substring(i, i + length);
      }
    }
    return null;
  }
  // use stack
  boolean isPalindrome(String s) {
    Stack<Character> stack = new Stack<Character>();
    for (int i = 0; i < s.length(); i ++) {
      if (i < s.length() / 2) { stack.push(s.charAt(i)); }
      if (s.length() % 2 != 0 && i == s.length() / 2) continue; // if length is odd, skip mid character
      if (i > s.length() / 2 - 1) { if (stack.pop() != s.charAt(i)) return false;}
    }
    return true;
  }

  /*
   * from center expand to both sides
   * two cases: expand from one char if length is odd, expand from two chars if length is even
   */
  public String longestPalindrome2(String s) {
    String res = "";
    // NOTE: i < s.length() - 1 will not check the last char,
    // and make no loop for length one string "a" and return "" instead of "a"
    for (int i = 0; i < s.length(); i++) {
      String odd = getPalindrome(s, i, i); // i = length - 1 check last char
      String even = getPalindrome(s, i, i + 1); // let getPalindrome method handle error case i + 1 = length
      if (odd.length() > res.length()) res = odd;
      if (even.length() > res.length()) res = even;
    }
    return res;
  }

  // even: left center != right center. odd: left center == right center
  String getPalindrome(String s, int lCenter, int rCenter) {
    int i = 0;
    while ((lCenter - i) > -1 && (rCenter + i) < s.length() // here handles i + 1 = length
        && s.charAt(lCenter - i) == s.charAt(rCenter + i)) {
      i++;
    }
    return s.substring(lCenter - i + 1, rCenter + i);
  }
}