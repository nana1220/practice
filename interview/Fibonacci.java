/*
 * Write function to compute Nth fibonacci number
 */

public class Fibonacci {

  static int fibonacci(int n) {
    int[] res = new int[n + 1];
    res[0] = 0;
    res[1] = 1;
    for (int i = 2; i < n + 1; i++) {
      res[i] = res[i - 1] + res[i - 2];
    }
    return res[n];
  }

  int fibonacci1(int n) {
    int fibNMinusOne = 1;
    int fibNMinusTwo = 0;
    int fibN = 0;
    for (int i = 2; i <= n; i++) {
      fibN = fibNMinusOne + fibNMinusTwo;
      fibNMinusTwo = fibNMinusOne;
      fibNMinusOne = fibN;
    }
  }


  static int fibonacciDP(int n, int[] cache) {
    if (cache[n] != -1) return cache[n];
    if (n == 0) return 0;
    if (n == 1) return 1;
    int res = fibonacciDP(n - 1, cache) + fibonacciDP(n - 2, cache);
    cache[n] = res;
    return res;
  }

  public static void main(String[] args) {
    System.out.println(fibonacci(10));
    int[] cache = new int[11];
    for (int i = 0; i < 11; i++) {
      cache[i] = -1;
    }
    System.out.println(fibonacciDP(10, cache));
  }

  // time O(n), space O(1)
  public int iterativeFibonacci(int n) {
    if (n == 1) return 0;
    else if (n == 2) return 1;
    int i = 0, j = 1, sum = 0;
    for (; (n - 2) != 0; --n) {
      sum = i + j;
      i = j;
      j = sum;
    }
    return sum;
  }

  // time T(n) = T(n-1) + T(n-2): O(2^n), space O(n)
  public int recursiveFibonacci(int n) {
    if (n == 1) return 0;
    else if (n == 2) return 1;
    return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
  }
  // time O(n), space O(n)
  public int memoizedFibonacci(int n) {
    if (n <= 0) return -1;
    else if (n == 1) return 0;
    else if (n == 2) return 1;
    if (memory[n - 1] == 0)
      memory[n - 1] = memoizedFibonacci(n - 1);
    if (memory[n - 2] == 0)
      memory[n - 2] = memoizedFibonacci(n - 2);
    return memory[n - 1] + memory[n - 2];
  }

}
