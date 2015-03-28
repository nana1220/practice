/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer,
then the reverse of 1000000003 overflows. How should you handle such cases?
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */

import java.lang.StringBuilder;

public class ReverseInteger {

  /*
   * convert int to string reverse char array and convert back
   */
  static public int reverse1(int x) {
    String s = String.valueOf(x);
    boolean sign = false;
    if (s.charAt(0) == '-') {
      sign = true;
      s = s.substring(1);
    }
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i > -1; i--) {
      sb.append(s.charAt(i));
    }
    int res = Integer.parseInt(sb.toString()); // NOTE: If reverse number overflow cannot parse
    if (sign) {
      return -1 * res;
    } else return res;
  }

  /*
   * res = res * 10 + abs(x) % 10
   */
  public int reverse2(int x) {
    if (x == Integer.MIN_VALUE) {
      return 0;
    }
    int res = 0;
    int sign = x > 0 ? 1 : -1;
    x = Math.abs(x);
    while (x > 0) {
      int digit = x % 10;
      if (sign == 1 && (Integer.MAX_VALUE - digit) / 10 < res) {
        return 0; // overflow
      }
      else if (sign == -1 && (Integer.MIN_VALUE + digit) / 10 > res) {
        return 0; // underflow
      }
      x /= 10;
      res = res * 10 + sign * digit;
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(~(1 << 31)); // 2147483647
  }
}