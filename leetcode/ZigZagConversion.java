/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

import java.lang.StringBuilder;
import java.util.Arrays;

public class ZigZagConversion {
  // bug!!
  static public String convert(String s, int nRows) {
    StringBuilder[] zz = new StringBuilder[nRows];
    Arrays.fill(zz, new StringBuilder());
    int idx = 0;
    int row = 0;
    boolean goDown = true;
    while (idx < s.length()) {
      zz[row].append(s.charAt(idx));
      if (goDown) row++;
      else row--;
      if (row % nRows == 0) {
        if (goDown) {
          row = nRows - 1;
          goDown = false;
        } else {
          row = 0;
          goDown = true;
        }
      }
      idx++;
    }
    StringBuilder sb = new StringBuilder();
    for (StringBuilder r : zz) {
      sb.append(r);
    }
    return sb.toString();
  }
}