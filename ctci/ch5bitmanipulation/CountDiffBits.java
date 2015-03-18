package ch5bitmanipulation;

import java.lang.Integer;
import java.lang.String;
import java.lang.System;

public class CountDiffBits {
  /*
   * Note: x & (x - 1) can clear the least significant bit in x,
   * so don't need to shift x repeatedly
   *
   * for (int xor = val1 ^ val2; xor != 0 ; xor = xor & (xor - 1)) {
   *   count++;
   * }
   */
  static int computeCounts(int val1, int val2) {
    int xor = val1 ^ val2;
    int count = 0;
    while (xor != 0) {
      if ((xor & 1) == 1) { // Note: count += (xor & 1); is better
        count++;
      }
      xor = xor >> 1; // shift xor repeatedly while checking the least significant bit
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(22));
    System.out.println(Integer.toBinaryString(222));
    System.out.println(computeCounts(22, 222));
  }
}