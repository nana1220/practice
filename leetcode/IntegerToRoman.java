/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
 */

public class IntegerToRoman {

  public String intToRoman(int num) {
    if (num == 0) return "";
    /*
     * the radix for Roman numerical are 1, 4, 5, 9, 10, 40, 50 , 90, 100, 400, 500, 900, 1000
     */
    int[] radix = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romans = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
        "IX", "V", "IV", "I"}; // in the order of next possible Roman numercial
    StringBuilder res = new StringBuilder();
    int idx = 0;
    while (num > 0) {
      if (num < radix[idx]) idx++; // check if num is bigger than next possible Roman numerical
      else {
        num -= radix[idx]; // each time substract the value of next possible Roman numercial
        res.append(romans[idx]); // and append the Roman letter to string
      }
    }
    return res.toString();
  }
}