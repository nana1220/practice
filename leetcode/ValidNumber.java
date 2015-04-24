/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */

// state check
public class Solution {
  public boolean isNumber(String s) {
    if (s==null || s.length()==0)
      return false;
    boolean noSign = false, noDot = false, noExp = false, num = false;
    int N = s.length(), i = 0, j=N-1;
    while (i<j && s.charAt(i)==' ') i++;    // trim leading whitespace
    while (i<j && s.charAt(j)==' ') j--;    // trim trailing whitespace
    if (i>j)   return false;    // all whitespace
    while(i<=j){
      char ch = s.charAt(i);
      if (ch>='0' && ch<='9'){
        num = true;
        noSign = true;        // no sign after num
      }else if (ch=='+' || ch=='-'){
        if (noSign)   return false;
        noSign = true;
      }else if (ch=='.'){
        if (noDot)    return false;
        noDot = true;
        noSign = true;        // no sign after num
      }else if (ch=='e' || ch=='E'){
        if (noExp || !num)    return false;
        noExp = true;
        noSign = false;       // allow sign after e
        num = false;
        noDot = true;         // no dot after e
      }else   return false;
      i++;
    }
    return num; // should have numeric characters
  }
}