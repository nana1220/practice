package ch5bitmanipulation;

import java.lang.Integer;
import java.lang.System;

/*
 * Given a positive integer, print its next smallest bigger number and next largest number
 * smaller number that have the same number of 1 bits in their binary representation
 *
 * Solution1: brute force, increase by one or devrease by one until find next same number of 1
 * Solution2: flip 1 to 0, flip another 0 to 1
 */
public class SameNumOfOnes {

  /*
   * flip lowest non-trailing 0 to 1, move all 1s to the right of this digit to the
   * right most side and flip highest 1 to 0.
   */
  static int findLarge(int positive) {
    int res = positive; // don't forget to initialize
    if (positive <= 0) {
      System.out.println("error");
    }
    boolean isTrailing = true;
    // must initialize because otherwise it is initialized in if statement
    // assume input is 32 bits int
    // nonTrail0pos > 31 means no non-trailing zeros
    int nonTrail0pos = 32;
    int numOf1beforeNonTrail0 = 0;
    for (int i = 0; i < 32; i++) {
      if (isTrailing) {
        if ((positive & (1 << i)) != 0) { // find first 1
          isTrailing = false;
          numOf1beforeNonTrail0++; // Careful!, don't forget
        }
      } else {
        if ((positive & (1 << i)) == 0) { // find first non-trailing 0
          res = positive | (1 << i); // flip it to 1
          nonTrail0pos = i; // memorize its position
          break;
        } else {
          numOf1beforeNonTrail0++;
        }
      }
    }
    numOf1beforeNonTrail0--; // one 1 need to flip to 0
    int mark1 = ~((1 << nonTrail0pos) - 1); // this form: 11111100000 to clear bits to 0s
    int mark2 = ((1 << numOf1beforeNonTrail0) - 1); // this form: 00000000011 to set bits to 1s
    return res & mark1 | mark2;
  }

  /*
   * find lowest non-trailing 1 bit, flip it to 0, move all trailing 1s to the right of this bit
   * flip highest trailing 0 to 1
   */
  static int findSmall(int positive) {
    int res = positive; // don't forget to initialize
    if (positive <= 0) {
      System.out.println("error");
      return -1;
    }
    int first0Pos = 0;
    int firstNonTrail1Pos = 1;
    while ((res & 1) == 1) { // find lowest 0
      // Note: res & 1 check the lowest bit, if not true res = res >> 1, don't forget assignment
      res = res >> 1;
      first0Pos++;
      firstNonTrail1Pos++;
    }
    if (res == 0) {
      System.out.println("error");
      return -1;
    }
    res = res >> 1;
    while ((res & 1) != 1) { // find non-trailing 1
      if (res == 0) {
        System.out.println("error");
        return -1;
      }
      res = res >> 1;
      firstNonTrail1Pos++;
    }
//    while ((res & (1 << first0Pos)) != 0) { // idx of lowest 0
//      first0Pos++;
//      if (first0Pos > 31) {
//        System.out.println("error");
//        break;
//      }
//    }
//    while ((res & (1 << (first0Pos + firstNonTrail1Pos))) != 1) { // idx of lowest non-trailing 1
//      firstNonTrail1Pos++;
//    }
    firstNonTrail1Pos = first0Pos + firstNonTrail1Pos;
    res = positive & (~(1 << firstNonTrail1Pos)); // flip 1 to 0
    int numOfTrail1 = first0Pos + 1; // add 1 because need to flip one 0 to 1
    int numOfTrail0 = firstNonTrail1Pos - numOfTrail1;
    int mask1 = ((1 << firstNonTrail1Pos) - 1) & (~((1 << numOfTrail0) - 1)); // form: 0000011111000 to set 1s
    int mask2 = ~((1 << numOfTrail0) - 1); // form: 1111111111000 to set 0s
    return res | mask1 & mask2;
  }


  public static void main(String[] args) {
    int positive = 222222;
    int large = findLarge(positive);
    int small = findSmall(positive);
    System.out.println("input: " + Integer.toBinaryString(positive));
    System.out.println("large: " + Integer.toBinaryString(large));
    System.out.println("small: " + Integer.toBinaryString(small));
  }
}