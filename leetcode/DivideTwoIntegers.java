/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */

/*
 * e.g. 80 / 7 = 2^3 * 7 + 2^1 *7 + 2^0 * 7  + 3 = (2^3+2^1+2^0)*7 + 3
 * so, divide by 2^n while qutient > 7, then substract 2^n * 7, result += 2^n
 */
public class DivideTwoIntegers {
  public int divide(int dividend, int divisor) {
    if (dividend == 0) {
      return 0;
    }
    if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // handle overflow to pass judge
    long d = Math.abs((long) dividend); // avoid overflow
    long s = Math.abs((long) divisor);
    int i = 31;
    int res = 0;
    while (d > 0 && i >= 0) { // NOTE: add d > 0 for early break
      if ((d >> i) >= s) {
        res += (1 << i);
        d -= (s << i); // NOTE: d = d - (s << i)
      }
      i--;
    }
    return (dividend > 0) ^ (divisor > 0) ? -res : res; // XOR, if sign different, return minus
  }
}