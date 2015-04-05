/*
Given two integers representing the numerator and denominator of a fraction, return
the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
 */

/*
The key insight here is to notice that once the remainder starts repeating, so does the divided result.
You will need a hash table that maps from the remainder to its position of the fractional part.
Once you found a repeating remainder, you may enclose the reoccurring fractional part with
parentheses by consulting the position from the table.
The remainder could be zero while doing the division. That means there is no repeating fractional
part and you should stop right away.

      0.16
6 )6  1.00
      0
      1 0       <-- Remainder=1, mark 1 as seen at position=0.
      - 6
        40      <-- Remainder=4, mark 4 as seen at position=1.
      - 36
         4      <-- Remainder=4 was seen before at position=1, so the fractional part which is 16
                   starts repeating at position=1 => 1(6).
 */

public class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) return "0";
    if (denominator == 0) return "";
    String ans = "";
    //如果结果为负数
    if ((numerator < 0) ^ (denominator < 0)) {
      ans += "-";
    }
    //下面要把两个数都转为正数，为避免溢出，int转为long
    long num = numerator, den = denominator;
    num = Math.abs(num); // if Integer.MIN_VALUE, must convert to long otherwise abs will overflow
    den = Math.abs(den);
    //结果的整数部分
    long res = num / den;
    ans += String.valueOf(res);
    //如果能够整除，返回结果
    long rem = (num % den) * 10; // zheshi ge weishu, fenshu chufa, meici yushu houmian jia ge 0 jiushi cheng 10
    if (rem == 0) return ans;
    //结果的小数部分
    HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    ans += ".";
    while (rem != 0) {
      //如果前面已经出现过该余数，那么将会开始循环
      if (map.containsKey(rem)) {
        int beg = map.get(rem); //循环体开始的位置
        String part1 = ans.substring(0, beg);
        String part2 = ans.substring(beg, ans.length());
        ans = part1 + "(" + part2 + ")";
        return ans;
      }
      //继续往下除
      map.put(rem, ans.length()); // store posision of remainder in the result, zheshi xiaoshu hou yiwei
      res = rem / den;
      ans += String.valueOf(res);
      rem = (rem % den) * 10;
    }
    return ans;
  }
}
