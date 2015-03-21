package ch9recursionanddynamicprogramming;

import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;

/*
 * iterate all characters of the string, take out the the chosen character and pass the rest part of
 * string to the next recursion call, also cancatenate the characters chosen from each recursion making
 * them a permutation
 * time: O(n!); space: recursive stack
 */
public class PermutationString {
  static void getPermutationStrings(String str, String result, ArrayList<String> stringSet) {
    if (str.length() == 0) {
      stringSet.add(result);
      return;
    }
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      String rest = str.substring(0, i) + str.substring(i + 1); // Note: String operation
      getPermutationStrings(rest, result + ch, stringSet);
    }
  }

  /*
   * permutation of length n String is equal to insert n-th char into every spots of permutation
   * of length n-1 String
   */
  static ArrayList<String> getPermString(String str) {
    ArrayList<String> res = new ArrayList<String>();
    if (str.length() == 1) {
      res.add(str); // don't return this guy, its return value is boolean
      return res;
    }
    char first = str.charAt(0);
    String rest = str.substring(1);
    ArrayList<String> words = getPermString(rest);
    for (String word : words) {
      for (int i = 0; i <= word.length(); i++) {
        String left = word.substring(0, i); // str.substring(0, 0) == ""
        String right = word.substring(i); // str.substring(str.length()) == ""
        res.add(left + first + right); // String = String + char + String
      }
    }
    return res;
  }

  /*
   * Solution 3: There is also a backtracking method
   */

  public static void main(String[] args) {
    ArrayList<String> res = new ArrayList<String>();
    getPermutationStrings("abcd", "", res);
    for (String s : res) {
      System.out.println(s);
    }
    System.out.println();
    ArrayList<String> res1 = getPermString("abcd");
    for (String s : res1) {
      System.out.println(s);
    }
  }
}