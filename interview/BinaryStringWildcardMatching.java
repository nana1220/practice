/*
Given a string of 0s, 1s, and ?s (wildcards), generate all 0-1 strings that match an input pattern.

For example:
0?1 --> 001, 011
1?00?101 -> 10000101, 10001101, 11000101, 11001101

You can generate the strings in any order that suits you.
 */

import java.util.List;
import java.util.ArrayList;

public class BinaryStringWildcardMatching {
  public List<String> wildcard(String str) {
    List<String> list = new ArrayList<String>();
    helper1(str, "", list); // or helper2(str, list);
    return list;
  }

  /*
   * time: O(2^n)
   */
  public void helper1(String str, String res, List<String> list) {
    if (str.length() == 0) {
      list.add(res);
      return;
    }
    char ch = str.charAt(0);
    if (ch != '?') helper1(str.substring(1), res + ch, list);
    else {
      helper1(str.substring(1), res + '1', list);
      helper1(str.substring(1), res + '0', list);
    }
  }

  public void helper2(String str, List<String> result){
    int index = str.indexOf("?");
    if (index == -1) {
      result.add(str);
      return;
    }
    char[] strCharArray = str.toCharArray();
    strCharArray[index] = '0';
    helper2(new String(strCharArray), result);
    strCharArray[index] = '1';
    helper2(new String(strCharArray), result);
  }
}