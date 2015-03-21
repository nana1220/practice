package ch9recursionanddynamicprogramming;

/*
 * Given a boolean expression consisting of the symbols 0, 1, &, |, and ^, and a desired
 * boolean result value, implement a function to count the number of ways of
 * parenthesizing the expression such that it evaluates to the desired result.
 */

import java.lang.Integer;
import java.lang.String;
import java.util.HashMap;

public class EvaluateBoolean {
  /*
   * iterate through each operator, centering on this operator parenthesizing both left side and right side,
   * recurse on both side
   */
  static int countEvalWays(String expr, boolean result) {
    // check error
    if (expr == null || expr.length() == 0) {
      return 0;
    }
    // base case
    if (expr.length() == 1) {
      if (expr.equals("1") && result) return 1; // count as one way
      else if (expr.equals("0") && !result) return 1; // count as one way
      else return 0; // not evaluted to desired result
    }
    int count = 0;
    for (int i = 1; i < expr.length() - 1; i++) { // from first operator to last operator
      if (result) {
        if (expr.charAt(i) == '&') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), true);
        }
        if (expr.charAt(i) == '|') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), true)
              + countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), false)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), true);
        }
        if (expr.charAt(i) == '^') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), false)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), true);
        }
      } else if (!result) {
        if (expr.charAt(i) == '&') {
          count += countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), true)
              + countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), false)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), false);
        }
        if (expr.charAt(i) == '|') {
          count += countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), false);
        }
        if (expr.charAt(i) == '^') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), true)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), false);
        }
      }
    }
    return count;
  }

  /*
   * DP: cache counting results
   * Note: Instead of using two cache one for true value one for false value, can also
   * tweak the cache key according to true or false. String key = expr + result;
   */
  static int countEvalWaysDP(String expr, boolean result,
                             HashMap<String, Integer> trueExprCache, HashMap<String, Integer> falseExprCache) {
    // check error
    if (expr == null || expr.length() == 0) {
      return 0;
    }
    // DP
    if (result && trueExprCache.containsKey(expr)) {
      return trueExprCache.get(expr);
    } else if (!result && falseExprCache.containsKey(expr)) {
      return falseExprCache.get(expr);
    }
    // base case
    if (expr.length() == 1) {
      if (expr.equals("1") && result) return 1; // count as one way
      else if (expr.equals("0") && !result) return 1; // count as one way
      else return 0; // not evaluted to desired result
    }
    int count = 0;
    for (int i = 1; i < expr.length() - 1; i++) { // from first operator to last operator
      if (result) {
        if (expr.charAt(i) == '&') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), true);
        }
        if (expr.charAt(i) == '|') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), true)
              + countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), false)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), true);
        }
        if (expr.charAt(i) == '^') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), false)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), true);
        }
      } else if (!result) {
        if (expr.charAt(i) == '&') {
          count += countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), true)
              + countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), false)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), false);
        }
        if (expr.charAt(i) == '|') {
          count += countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), false);
        }
        if (expr.charAt(i) == '^') {
          count += countEvalWays(expr.substring(0, i), true) * countEvalWays(expr.substring(i + 1), true)
              + countEvalWays(expr.substring(0, i), false) * countEvalWays(expr.substring(i + 1), false);
        }
      }
    }
    if (result) {
      trueExprCache.put(expr, count);
    } else {
      falseExprCache.put(expr, count);
    }
    return count;
  }

  public static void main(String[] args) {
    String expr = "0^0|1&1^1|0|1";
    boolean result = true;
    System.out.println(countEvalWays(expr, result));
    System.out.println(countEvalWaysDP(expr, result, new HashMap<String, Integer>(), new HashMap<String, Integer>()));
  }
}