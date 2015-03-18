package ch5bitmanipulation;

import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class BinaryReprDouble {
  /*
   * or compare with 1/2, 1/4, 1/8,... bit by bit until double becomes zero
   */
  static String printBinary(double d) {
    if (d >= 1 || d <= 0) {
      return "error";
    }
    StringBuilder res = new StringBuilder();
    res.append("0.");
    while (d != 0) {
      if (res.length() == 32) {
        return "error";
      }
      double temp = d * 2; // decimal number times 10 to move one digit left, binary times 2
      if (temp >= 1) { // Note: shoule be >=, not just >
        res.append(1);
        d = temp - 1;
      } else {
        res.append(0);
        d = temp;
      }
    }
    return res.toString();
  }

  public static void main(String[] args) {
    System.out.println(printBinary(0.22));
    System.out.println(printBinary(0.625));
  }
}