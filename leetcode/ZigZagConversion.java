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
/*
. Use a pointer to current row, append the char to string that corresponds to current row, and change direction when pointer touch 0 or length-1.
 */
public class Solution {
  public String convert(String s, int nRows) {
    if (s==null || s.length()==0 || nRows<=0)   return "";
    if (nRows == 1) return s;       // should return here, otherwise error below
    ArrayList<StringBuilder> rows = new ArrayList<StringBuilder>();
    for (int i=0; i<nRows; i++) rows.add(new StringBuilder());
    int r = 0;
    boolean down = true;
    for (int i=0; i<s.length(); i++){
      rows.get(r).append(s.charAt(i));
      r = down ? r+1:r-1;
      if (r==nRows-1) down=false;
      if (r==0)   down=true;
    }
    StringBuilder res = new StringBuilder();
    for (StringBuilder row : rows)  res.append(row);
    return res.toString();
  }
}


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