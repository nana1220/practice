/*
Implement a method rand7() given rand5(). That is, given a method that generates
a random number between 0 and 4 (inclusive), write a method that generates a
random number between 0 and 6 (inclusive).
*/

package ch17moderate;

import java.util.Random;

public class RandomSeven {

  /*
   * use rand5() generate a set of uniform random numbers, the range needs to be larger than 7.
   * generate two dimensional points (x, y) where x~rand5, and y~rand5, thus every point from (0, 0) to
   * (4, 4) have a chance of 1 / 25.
   * Map two dimensional matrix to one dimension by x * 5 + y, so we have 0 ~ 24, we only return 0~7,
   * or more efficiently we return 0~20 and mod return value by 7..
   */
  static int rand7() {
    int rand = 5 * rand5() + rand5();
    while (rand > 20) {
      rand = 5 * rand5() + rand5();
    }
    return rand % 7;
  }

  static int rand5() {
    return new Random().nextInt(5);
  }
}