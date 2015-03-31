/*
Part 0:
Write a method to return all squares up to a given number:

8 -> 0, 1, 4
9 -> 0, 1, 4, 9

Part 1:
Write a method which returns whether a number is a sum of two squares.

isSumOfTwoSquares(13) -> true
13 = 9 + 4

isSumOfTwoSquares(14) -> false.

isSumOfTwoSquares(25) -> true
25 = 25 + 0

isSumOfTwoSquares(8) -> true
8 = 4 + 4

Part 2:
Write a method which expresses a number as a sum of squares, using as few terms as possible.

For example
14 = 9 + 4 + 1.
13 = 9 + 4
12 = 4 + 4 + 4 (NOT 9 + 1 + 1 + 1)
5 = 4 + 1
 */

import java.lang.Integer;
import java.util.*;
import java.util.ArrayList;

public class AllSquaresUpToANumber {
  // part 0
  List<Integer> squares(int num) {
    List<Integer> res = new ArrayList<Integer>();
    int i = 1;
    while(i * i <= num) {
      res.add(i * i);
      i++;
    }
    return res;
  }
  // part 1: O(sqrt(num / 2))
  boolean isSumOfTwoSquares(int num) {
    for (int i =0; i * i <= num / 2; i++) { // num / 2 is enough
      int tmp = num - i*i;
      if ((int) Math.sqrt(tmp) * (int) Math.sqrt(tmp) == tmp) return true;

    }
    return false;
  }

  // part 2
  static List<Integer> sumOfSquares(int num) {
    ArrayList<ArrayList<Integer>> cache = new ArrayList<ArrayList<Integer>>();
    // NOTE: initialze cache first
    for (int i = 0; i <= num; i++) {
      cache.add(new ArrayList<Integer>());
    }
    cache.get(1).add(1);
    for (int i = 2; i <= num; i++) {
      if ((int) Math.sqrt(i) * (int)Math.sqrt(i) == i) {
        cache.get(i).add(i);
      } else {
        int termNum = Integer.MAX_VALUE;
        ArrayList<Integer> res = null;
        for (int j = 1; j * j < i; j++) {
          ArrayList<Integer> tmp = new ArrayList<Integer>(cache.get(i - j * j)); //DON'T forget clone()
          tmp.add(j * j);
          if (tmp.size() < termNum) {
            res = tmp;
            termNum = tmp.size(); // DON'T FORGET MODIFY SIZE
          }
        }
        cache.get(i).addAll(res);
      }
    }
    return cache.get(num);
  }

  public static void main(String[] args) {
    System.out.println(sumOfSquares(14).toString());
    System.out.println(sumOfSquares(12).toString());
    System.out.println(sumOfSquares(74).toString());
  }
}