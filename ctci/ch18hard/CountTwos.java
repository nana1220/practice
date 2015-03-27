/*
 Write a method to count the number of 2s between 0 and n
 */

package ch18hard;

class CountTwos {
  /*
   * brute force
   */
  static int countTwo(int n) {
    int count = 0;
    for (int i = 0; i <= n; i++) {
      // Can separate out count part as a single function
      while (i > 0) {
        if (i % 10 == 2) count++; // count least significant digit
        i /= 10; // shift right
      }
    }
    return count;
  }
  /*
   * optimize, count the 2s in all numbers at each digit
   * 2 provide 1 two, 2x provide 10 two, 2xx provide 100 two
   * e.g. countTwo(182) = (18+1) + 20 =39
   * Trickt part is that in 123, '2' contributes only 5 two (20,21,22,23, 4+1)
   */
}