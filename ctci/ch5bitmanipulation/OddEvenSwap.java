package ch5bitmanipulation;

import java.lang.Integer;

public class OddEvenSwap {
  static int swapBits(int val) {
    int even = 0xaaaaaaaa & val; // a is 1010
    int odd = (0xaaaaaaaa >> 1) & val; // Note: use 0x55555555, 5 is 0101
    even = even >> 1;
    odd = odd << 1;
    return even | odd;
  }

  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(222222));
    System.out.println(Integer.toBinaryString(swapBits(222222)));
  }
}