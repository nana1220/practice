/*
 * Write a method which finds the maximum of two numbers. You should not use
 * if-else or any other comparison operator.
 */

package ch17moderate;

import java.lang.System;

public class MaxOfTwoNumber {

  /*
   * Replace if else by a * x + b * x where x = 1 or 0, x = 1 means true then x ^ 1 = 0 means false
   * Note: Doesn't work!! when a - b overflows, if a > b overflow will turn the sign to 1
   */
  static int max(int a, int b) {
    int sign = ((a - b) >> 31) & 1; // if a > b sign = 0, signFlipped = 1, return a * signFlipped + b * sign
    int signFlipped = sign ^ 1; // XOR flip 1 to 0, 0 to 1
    return a * signFlipped + b * sign;
  }

  // only different in signs can lead to overflow
  // so when different in signs don't use a - b to conclude result, compare their sign instead
  static int maxOvercomeOverflow(int a, int b) {
    int c = a - b;
    int sa = sign(a), sb = sign(b), sc = sign(c);
    int diffSign = sa ^ sb; // diffSign = 1 if sa and sb are different
    // a,b have different sign, sa is 0 return a
    // a,b have same sign, sc is 0 return a
    int aIsMax = diffSign * flip(sa) + flip(diffSign) * flip(sc);
    return aIsMax * a + flip(aIsMax) * b; // flip(aIsMax) means else when a is not max
  }

  // return the most significant bit of num
  static int sign(int num) {
    return (num >> 31) & 1;
  }

  // flip one bit
  static int flip(int num) {
    return num ^ 1;
  }

  public static void main(String[] args) {
    System.out.println(max(2, 22));
    System.out.println(maxOvercomeOverflow(2, 22));
  }
}