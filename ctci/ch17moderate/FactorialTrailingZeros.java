package ch17moderate;

/*
 * Write an algorithm which computes the number of trailing zeros in n factorial.
 */

public class FactorialTrailingZeros {
  // Note: 25 contribute two 5, 125 contribute 3
  int computeZeros(int n) {
    int factor = 5;
    int res = 0;
    while(n / factor > 0) {
      res += n / factor; // count how many multiples of 5, 25, 125,...
      factor *= 5;
    }
    return res;
  }
}