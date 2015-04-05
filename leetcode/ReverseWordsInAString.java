/*
    Given an input string, reverse the string word by word.
    For example,
    Given s = "the sky is blue",
    return "blue is sky the".
*/

// maintain a stack to hold all the words parsed from the origianl string
// then, construct the reversed string from stack
// time: O(n); space: O(n)
public class Solution {
  public String reverseWords(String s) {
    if (s==null || s.length()==0)
      return "";
    Stack<String> st = new Stack<String>();
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<s.length(); i++){
      if (s.charAt(i)!=' ')
        sb.append(s.charAt(i));
      else{
        if (sb.length()>0){
          st.push(sb.toString());
          sb.delete(0, sb.length());
        }
      }
    }
    if (sb.length()>0)
      st.push(sb.toString());
    sb.delete(0,sb.length());
    while (!st.isEmpty())
      sb.append(st.pop()+" ");
    if (sb.length()>0) // check here
      sb.deleteCharAt(sb.length()-1);
    return sb.toString();
  }
}
// solution 2
class Solution {
  public String reverseWords(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    // split to words by space
    String[] arr = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = arr.length - 1; i >= 0; --i) {
      if (!arr[i].equals("")) {
        sb.append(arr[i]).append(" ");
      }
    }
    return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1); // NOTE: remove last " "
  }