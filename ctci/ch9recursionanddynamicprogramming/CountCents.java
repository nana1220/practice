package ch9recursionanddynamicprogramming;

/*
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents)
 * and pennies (1 cent), write code to calculate the number of ways of representing n
 * cents
 */

import java.lang.Integer;
import java.lang.System;
import java.util.LinkedList;

public class CountCents {

  /*
   * once use a smaller face value coin, can not use bigger face value coins again,
   * otherwise there will be duplicated counts
   */
  static class Count {
    int count = 0;
  }
  static void countCents(int n, int minValueInUse, Count count) {
    // base case: this path can represent, count one
    if (n == 0) {
      count.count += 1;
      return;
    }
    // prunning case
    if (n < 0) {
      return;
    }
    if (minValueInUse == 25) {
      countCents(n - 25, 25, count);
      countCents(n - 10, 10, count);
      countCents(n - 5, 5, count);
      countCents(n - 1, 1, count);
    }
    if (minValueInUse == 10) {
      countCents(n - 10, 10, count);
      countCents(n - 5, 5, count);
      countCents(n - 1, 1, count);
    }
    if (minValueInUse == 5) {
      countCents(n - 5, 5, count);
      countCents(n - 1, 1, count);
    }
    if (minValueInUse == 1) {
      countCents(n - 1, 1, count);
    }
  }

  /*
   * Head and tail recursion, head is the biggest face valie coin, tail are the rest coins.
   * Iterate through differnt amount of head coin then recurse to next level, make the next biggest
   * face value coin to be head and rest to be tail
   */
  static int countCents2(int n, int[] faceValue, int headIndex) {
    int head = faceValue[headIndex];
    // base case: faceValue is one cent, use all one cents, this is leaf, path end, count one
    // note that might not use one cent, if n == 0, but still get the leaf, path end, count one
    if (head == 1) {
      return 1;
    }
    int ways = 0;
    headIndex++; // get the head of tail
    // How many iterations means how many ways since in the end each path will return one
    // This is acutally counting how many different paths in the subtree starting from the current node
    // Each subtree will return one
    for (int i = 0; i * head <= n; i++) {
      ways += countCents2(n - i * head, faceValue, headIndex);
    }
    return ways;
  }

  public static void main(String[] args) {
    Count count = new Count();
    countCents(100, 25, count);
    System.out.println(count.count);

    int[] faceValue = new int[] {25, 10, 5, 1};
    System.out.println(countCents2(100, faceValue, 0));
  }
}