/*
given a sorted array x: double[], and y= ax^2 + bx + c, return sorted array of y
O(n) time
 */
import java.util.*;

class Solution {
  double[] getSortedY(double[] x, double a, double b, double c) {
    double mid = - b / ( 2 * a);
    int i = 0;
    if (a > 0) {
      while (x[i] > mid) i++;
    } else if (a < 0) {
      while (x[i] < mid) i++;
    } else {
      // a = 0
    }
    double[] left = Arrays.copyOfRange(x, 0, i); // assume a > 0, so left side descending
    double[] right = Arrays.copyOfRange(x, i, x.length); // right side ascending
    int l = left.length;
    int r = 0;
    int j = 0;
    double[] y = new double[x.length];
    while(l >= 0 && r < right.length) {
      if (left[l] <= right[r]) {y[j] = left[l]; l--; j++;}
      else { y[j] = right[r]; r++; j++;}
    }
    if(l >= 0) while (l >= 0) y[j] = left[l--];
    if(r < right.length) System.arraycopy(right, r, y, j, right.length - r);
    return y; // then apply ax^2 + bx + c to y
  }
}
