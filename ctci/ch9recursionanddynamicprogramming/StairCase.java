package ch9recursionanddynamicprogramming;

/*
 * A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or
 * 3 steps at a time. Implement a method to count how many possible ways the child
 * can run up the stairs.
 */
public class StairCase {

  public static int countWaysDP(int n, int[] map) {
    if (n < 0) {
      return 0;
    } else if (n == 0) {
      return 1;
    } else if (map[n] > -1) {
      return map[n];
    } else { // last step can be finished by either 1 step, 2 step and 3 step
      map[n] = countWaysDP(n - 1, map) +
          countWaysDP(n - 2, map) +
          countWaysDP(n - 3, map);
      return map[n];
    }
  }

  /*
   * top-down approach: starting from n
   */
  static int calcWaysRecur(int n, int[] cache) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    if (n == 3) {
      return 4;
    }
    if (cache[n] == 0) { // if not cached yet, cache and return
      cache[n] = calcWaysRecur(n - 1, cache) + calcWaysRecur(n - 2, cache) + calcWaysRecur(n - 3, cache);
    }
    return cache[n];
  }

  /*
   * iterative impl.
   */
  static int calcWaysIter(int n) {
    int[] cache = new int[n + 1];
    cache[1] = 1;
    cache[2] = 2;
    cache[3] = 4;
    for (int i = 4; i <= n; i++) {
      cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
    }
    return cache[n];
  }

  public static void main(String[] args) {
    int n = 22;
    int[] cache = new int[23];
    System.out.println(calcWaysRecur(22, cache));
    System.out.println(calcWaysIter(22));
  }
}