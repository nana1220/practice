/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */

// TLE
public class WildcardMatching {
  public static boolean isMatch1(String s, String p) {
    if (s.length() == 0 && p.length() == 0) return true;
    if (s.length() == 0 || p.length() == 0) return false;
    if (p.substring(0, 1).equals("?") || p.substring(0, 1).equals(s.substring(0, 1)))
      return isMatch1(s.substring(1), p.substring(1));
    if (p.substring(0, 1).equals("*")) {
      if (isMatch1(s, p.substring(1))) return true; // either ignore '*'
      else return isMatch1(s.substring(1), p); // or keep '*' match out first char of s
    }
    return false;
  }


// The difficulty mainly lies in that '*' can match with a sequence of characters
// The trick here is to maintain two pointers to hold the backup positions for pointers i,j in s,p
// Once we find that s.substring(i, j) can not be replaced by '*', we try s.subtring(i+1,j).

  static public boolean isMatch2(String s, String p) {
    int i = 0;
    int j = 0;
    int star = -1;
    int mark = -1;
    while (i < s.length()) {
      if (j < p.length()
          && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
        ++i;
        ++j;
      } else if (j < p.length() && p.charAt(j) == '*') { // only need to mark most recent '*', ab*cd* is better than ab*
        star = j++;
        mark = i; // first time meet '*', match nothing in s
      } else if (star != -1) {
        j = star + 1; // if not match og back to '*'
        i = ++mark; // match out one more char in s
      } else {
        return false;
      }
    }
    while (j < p.length() && p.charAt(j) == '*') {
      ++j;
    }
    return j == p.length();
  }

  public static void main(String[] args) {
    System.out.println(isMatch1("abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab", "*aabb***aa**a******aa*"));
    System.out.println(isMatch2("abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab", "*aabb***aa**a******aa*"));
  }
}