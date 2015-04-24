/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
 */

public class ValidPalindrome {
  static public boolean isPalindrome(String s) {
    s = s.toLowerCase();
    int i=0;
    int j= s.length() -1;
    while(i < j){
      while(i<j && !Character.isLetterOrDigit(s.charAt(i))) i++;
      while(i<j && !Character.isLetterOrDigit(s.charAt(j))) j--;
      if(i>=j) break; // Note
      if(s.charAt(i) != s.charAt(j)) return false;
      i++; // remember to update i and j
      j--;
    }
    return true;
  }

  // use regex then stack
  public boolean isPalindrome(String s) {
    s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

    int len = s.length();
    if (len < 2)
      return true;

    Stack<Character> stack = new Stack<Character>();

    int index = 0;
    while (index < len / 2) {
      stack.push(s.charAt(index));
      index++;
    }

    if (len % 2 == 1)
      index++;

    while (index < len) {
      if (stack.empty())
        return false;

      char temp = stack.pop();
      if (s.charAt(index) != temp)
        return false;
      else
        index++;
    }

    return true;
  }

}