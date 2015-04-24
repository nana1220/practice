/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
 */

// sort strings according to compare s1+s2 and s2+s1
public class Solution {
  public String largestNumber(int[] nums) {
    Comparator<String> comp = new Comparator<String>(){
      public int compare(String s1, String s2){
        String s12 = s1+s2;
        String s21 = s2+s1;
        for(int i=0; i<s12.length(); i++){
          if(s21.charAt(i) > s12.charAt(i)) return 1;// sort from large to small
          else if(s21.charAt(i) < s12.charAt(i)) return -1;
        }
        return 0;
      }
    };
    ArrayList<String> strs = new ArrayList<String>();
    for(int i : nums){
      strs.add(""+i);
    }
    Collections.sort(strs, comp);
    StringBuilder res = new StringBuilder();
    for(String s : strs){
      res.append(s);
    }
    int i=0;
    while(i<res.length() && res.charAt(i) =='0') i++; // remove heading zeros, Dont' forget range check i<res.length()
    if (i==res.length()) return "0";
    return res.substring(i);
  }
}