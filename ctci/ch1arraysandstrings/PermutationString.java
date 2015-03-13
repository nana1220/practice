package ch1arraysandstrings;

import java.lang.Character;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PermutationString {

  public static void main(String[] args) {
    System.out.println(isPermutation1(args[0], args[1]));
    System.out.println(isPermutation2(args[0], args[1]));
    System.out.println(isPermutation3(args[0], args[1]));
  }

  /*
   * sort two strings then check if they are equal
   * time: O(nlog(n)); space O(n)
   */
  static boolean isPermutation1(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }
    char[] val1 = str1.toCharArray();
    char[] val2 = str2.toCharArray();
    Arrays.sort(val1);
    Arrays.sort(val2);
    for (int i = 0; i < str1.length(); i++) {
      if (val1[i] != val2[i]) {
        return false;
      }
    }
    return true;
  }

  /*
   * count each char in both strings, see if all counts are equal, use Map for count storage
   * time: O(n)
   */
  static boolean isPermutation2(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }
    Map<Character, Integer> count1 = new HashMap<Character, Integer>();
    Map<Character, Integer> count2 = new HashMap<Character, Integer>();
    for (int i = 0; i < str1.length(); i++) {
      char ch1 = str1.charAt(i);
      if (count1.containsKey(ch1)) {
        count1.put(ch1, count1.get(ch1) + 1);
      } else {
        count1.put(ch1, 1);
      }
      char ch2 = str2.charAt(i);
      if (count2.containsKey(ch2)) {
        count2.put(ch2, count2.get(ch2) + 1);
      } else {
        count2.put(ch2, 1);
      }
    }
    return count1.equals(count2);
  }

  /*
   * use marker (frequency array) to store counts, assume ASCII or UTF8, so length is  2^8
   */
  static boolean isPermutation3(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }
    int[] count = new int[256];
    for (int i = 0; i < str1.length(); i++) {
      count[(int) str1.charAt(i)]++;
      count[str2.charAt(i)]--;
    }
    for (int val : count) {
      if (val != 0) {
        return false;
      }
    }
    return true;
  }
}