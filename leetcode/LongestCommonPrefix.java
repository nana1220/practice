/*
Write a function to find the longest common prefix string amongst an array of strings.
 */

public class LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if(strs.length == 0) return "";
    for (String s : strs) {
      if (s.length() == 0) return "";
    }
    int i = 0;
    StringBuilder sb = new StringBuilder();
    while(true) {
      if ((strs[0].length() == i)) return sb.toString(); // always check string before call charAt(i)
      char ch = strs[0].charAt(i);
      for (String s : strs) {
        if ((s.length() == i) || (s.charAt(i) != ch)){ // always check string before call charAt(i)
          return sb.toString();
        }
      }
      sb.append(ch);
      i++;
    }
  }
}