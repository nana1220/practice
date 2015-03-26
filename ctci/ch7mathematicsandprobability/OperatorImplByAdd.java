/*
Write methods to implement the multiply, subtract, and divide operations for inte-
gers. Use only the add operator.
 */
package ch7mathematicsandprobability;

public class OperatorImplByAdd {
  /*
   * need to implement negate function since not allowed * -1
   */
  static int negate(int a) {
    int neg = 0;
    int sign = a > 0 ? -1 : 1;
    while (a != 0) {
      a += sign;
      neg += sign;
    }
    return neg;
  }

  static int minus(int a, int b) {
    return a + negate(b);
  }

  static int multiply(int a, int b) {
    boolean isPositive = true;
    if (b < 0) {
      isPositive = false;
      b = negate(b);
    }
    int res = 0;
    while (b != 0) res += a;
    if (!isPositive) {
      return negate(res);
    }
    return res;
  }

  /*
   * need to consider signs of a and b
   */
  static int divide(int a, int b) {
    int res = 0;
    while (multiply(b, 0) <= a) {
      res++;
    }
    return minus(res, 1);
  }
}