/*
Write a method to randomly generate a set of m integers from an array of size n.
Each element must have equal probability of being chosen.
 */

package ch18hard;

import java.util.Random;
import java.util.Arrays;
/*
 * Suppose we have an algorithm that can pull a random set of m elements from an array
 * of size n - 1. How can we use this algorithm to pull a random set of m elements from
 * an array of size n.
 */
public class GenerateIntegers {
  /*
   * swap
   */
  static int[] generateIter1(int[] arr, int m) {
    for (int i = m; i < arr.length; i++) {
      int pos = new Random().nextInt(i + 1);
      int temp = arr[pos];
      arr[pos] = arr[i];
      arr[i] = temp;
    }
    int[] res = new int[m];
    System.arraycopy(arr, 0, res, 0, m);
    return res;
  }

  /*
   * copy to clone() instead of swap
   */
  static int[] generateIter2(int[] arr, int m) {
    int[] res = Arrays.copyOfRange(arr, 0, m);
    for (int i = m; i < arr.length; i++) {
      int pos = new Random().nextInt(i + 1);
      if (pos < m)
        res[pos] = arr[i];
    }
    return res;
  }

  static int[] generateRecur1(int[] arr, int m, int n) {
    if (n == m) {
      int[] res = new int[m];
      System.arraycopy(arr, 0, res, 0, m); // use Arrays.copyOfRange
      return res;
    }
    /*
     * swap first then recurse
     */
    int pos = new Random().nextInt(n);
    int temp = arr[pos];
    arr[pos] = arr[n - 1];
    arr[n - 1] = temp;
    return generateRecur1(arr, m, n - 1);
  }

  public int[] generateRecur2(int[] arr, int m, int n) {
    if (n == m)
      return Arrays.copyOfRange(arr, 0, m); // this array copy is better
    if (n > m) { // error check
      /*
       * recurse first, then swap
       */
      int[] res = generateRecur2(arr, m, n - 1);
      int pos = new Random().nextInt(n);
      if (pos < m) // only swap if pos < m
        res[pos] = arr[n - 1];
      return res;
    }
    return null;
  }
}