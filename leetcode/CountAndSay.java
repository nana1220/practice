/*
    The count-and-say sequence is the sequence of integers beginning as follows:
    1, 11, 21, 1211, 111221, ...
    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.
    Given an integer n, generate the nth sequence.
    Note: The sequence of integers will be represented as a string.
*/

public class Solution {
  public String countAndSay(int n) {
    String res = "1";
    while(n>1) {
      res = nextValue(res);
      n--;
    }
    return res;

  }

  String nextValue(String num) {
    StringBuilder sb = new StringBuilder();
    char[] c = num.toCharArray();
    int count = 1;
    for (int i=0; i < num.length(); ) {
      int j = i+1;
      while(j< num.length() && c[j] == c[i]){ // remember to check range of j first
        count++;
        j++;

      }
      sb.append(count);
      sb.append(c[i]);
      i=j;
      count=1;
    }
    return sb.toString();
  }
}

// Maintain a counter. Increment the counter if there's repeating characters,
// otherwise, reset the counter to 1.
// Each time, build the current string based on previous string
// time: O(n^2); space: O(n^2)
// If goes back, first number is count, second number is value 111221 -> 1211
public class Solution {
  public String countAndSay(int n) {
    if (n==0)   return "";
    StringBuilder prev = new StringBuilder();
    prev.append(1);
    while (n>1){
      StringBuilder curr = new StringBuilder();
      int count = 1;
      for (int i=0; i<prev.length(); i++){
        if (i+1<prev.length() && prev.charAt(i)==prev.charAt(i+1))
          count++;
        else{
          curr.append(count);
          curr.append(prev.charAt(i));
          count = 1;
        }
      }
      prev = curr;
      n--;
    }
    return prev.toString();
  }
}