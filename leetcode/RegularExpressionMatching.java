/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).
The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
 */

/*
 * NOTE1: “.*” matches the string “ab”. “.*” means repeat the preceding element 0 or more times.
 * Here, the “preceding” element is the dot character in the pattern, which can match any characters.
 * Therefore, the regex pattern “.*” allows the dot to be repeated any number of times, which matches
 * any string (even an empty string). ".*" = "........" each "." can match any characters.
 *
 * NOTE2: "b*", “*” can also repeat the preceding element 0 times, so "b*" can match empty string "".
 * "." can match any single character but does not match empty ""
 */

public class RegularExpressionMatching {
  /*
   * The entire pattern must match the entire input string, not that input string just a substring
   * of the pattern
   */
  public boolean isMatch(String s, String p) {
    if (p.length() == 0) return s.length() == 0;
    if (p.length() == 1) {
      return ((s.length() == 1) && (s.equals(p) || p.charAt(0) == '.')); // "." doesn't match ""
    }
    if (s.length() == 0) return ((p.charAt(1) == '*') && isMatch(s, p.substring(2)));
    if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') {
      if (p.charAt(1) == '*') {
        // '*' is bound to its preceding char, when a 'char' matches 'char*',
        // you can either move on to next char of original string and compare to pattern string
        // or ignore 'char*' move on to next char of pattern string and compare to original string
        return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
        // if second char is not '*', compare their substrings starting from next char
      } else {
        return isMatch(s.substring(1), p.substring(1));
      }
    } else {
      // if first char doesn't match, pattern string's second char must be '*'
      // the first two char of pattern string can be ignored
      return (p.charAt(1) == '*') && isMatch(s, p.substring(2));
    }
  }
}