/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
 */

// base 10 to base 26 converstion,
// NOTE!!: n-1 to make A corresponds to remainder 0, and so Z corresponds to remainder 25 instead of 0
public class Solution {
  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while(n>0){
      int rem = (n-1)%26; // first remainder corresponds to 26^0, that is least significant digit
      n = (n-1)/26;
      char c = (char)(rem+'A');
      sb.append(c);
    }
    String res ="";
    for(int i=sb.length()-1; i>=0;i--){
      res += sb.charAt(i);
    }
    return res;
  }
}
// while循环里面的n–，因为26进制里最小对应的是10进制里面的1，不是0
public class Solution {
  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while (n>0) {
      n--;
      char ch = (char) (n % 26 + 'A');
      n /= 26;
      sb.append(ch);
    }
    sb.reverse(); // sb has reverse method
    return sb.toString();
  }
}