/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 */

public class RomanToInteger {

  /*
   * a simplified trie tree
   */
  public int romanToInt(String s) {
    int[] radix = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romans = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
        "IX", "V", "IV", "I"};
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("I", 1);
    map.put("IV", 4);
    map.put("V", 5);
    map.put("IX", 9);
    map.put("X", 10);
    map.put("XL", 40);
    map.put("L", 50);
    map.put("XC", 90);
    map.put("C", 100);
    map.put("CD", 400);
    map.put("D", 500);
    map.put("CM", 900);
    map.put("M", 1000);
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      if ((i + 1) < s.length()) {
        Integer num = map.get(s.substring(i, i + 2));
        if (num != null) {
          res += num;
          i++; // NOTE: find a two letter radix, need to add 1 to i for next loop
        } else {
          res += map.get(s.substring(i, i + 1));
        }
      } else {
        res += map.get(s.substring(i, i + 1));
      }
    }
    return res;
  }
}