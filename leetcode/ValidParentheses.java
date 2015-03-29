/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the
input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

import java.lang.Character;

public class ValidParentheses {
  /*
   * use stack
   * time: O(n); space: O(n)
   */
  public boolean isValid(String s) {
    Stack<Character> parens = new Stack<Character>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') parens.push(c);
      if (c == ')' || c == ']' || c == '}') {
        if (parens.isEmpty()) return false; // NOTE:!! stack is empty also means false
        else {
          if ((c == ')' && parens.pop() != '(') || (c == ']' && parens.pop() != '[')
              || (c == '}' && parens.pop() != '{')) {
            return false;
          }
        }
      }
    }
    if (!parens.isEmpty()) {
      return false;
    }
    return true;
  }
}