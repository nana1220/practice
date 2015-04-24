/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
 */

public class Solution {
  public int titleToNumber(String s) {
    int res=0;
    int base =1;
    for(int i=s.length()-1; i>=0; i--){
      int val = (int) (s.charAt(i) -'A') +1;
      res += base * val;
      base *= 26;
    }
    return res;
  }
}