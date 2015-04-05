/*
Given two strings containing digits, return the one which represents the largest integer once the
digits have been sorted in non-increasing order.

Exampli:
“245” -> 542
“178” -> 871
return 178
 */

import java.lang.String;

public class CountingSort {
  /*
   * count who has more bigger number, use array index mapping to 0~9
   */
  static String getBig(String num1, String num2) {
    if (num1.length() > num2.length()) return num1;
    if (num1.length() < num2.length()) return num2;
    int[] count1 = countingArray(num1);
    int[] count2 = countingArray(num2);
    for (int i = 9; i >= 0; i--) {
      if (count1[i] > count2[i]) return num1;
      if (count1[i] < count2[i]) return num2;
    }
    return null;
  }

  // 10 elements array, each element represents a digit from 0 to 9
  // array[0] represents number 0,.., , array[9] represents number of 9
  // count the number of every digit of the input number, store in array
  static int[] countingArray(String num) {
    int[] res = new int[10];
    for (int i = 0; i < num.length(); i++) {
      char digit = num.charAt(i);
      res[digit - '0']++;
    }
    return res;
  }

  public static void main(String[] args) {
    String num1 = "245";
    String num2 = "178";
    System.out.println(getBig(num1, num2));
  }
}