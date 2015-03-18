package ch5bitmanipulation;

import java.lang.Integer;
import java.lang.System;

/*
 * need to consider the situation when x1 and x2 are in same byte
 */
public class DrawHorizontalLine {
  static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
    int heightIdx = y * width / 8;
    int leftByteIdx = x1 / 8 + (x1 % 8 != 0 ? 1 : 0); // Note: + 1 only when x % 8 != 0
    int leftOffset = 8 - x1 % 8; // from left to right
    int rightByteIdx = x2 / 8 + (x1 % 8 == 0 ? 1 : 0); // Note also: + 1 only when
    int rightOffset = x1 % 8; // from right to left
    for (int i = leftByteIdx; i < rightByteIdx; i++) {
      // Don't forget convert to byte
      screen[heightIdx + i] = (byte) 0xff; // set full bytes to ones
    }
    if (leftOffset != 8) {
      // Note: type conversion
      // byte startMask = (byte) 0xff >> leftOffset; no need >>>
      screen[heightIdx + leftByteIdx - 1] = (byte) ((1 << leftOffset) - 1); // 00011111
    }
    if (rightOffset > 0) {
      // byte endMask = (byte) 0xff << rightOffset;
      screen[heightIdx + rightByteIdx] = (byte) ~((1 << (8 - rightOffset)) - 1); // 11100000
    }
  }

  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(0xff >>> 2));
    System.out.println(Integer.toBinaryString(0xff >> 2)); // same as above since 0xff is non-negative value
    System.out.println(Integer.toBinaryString(0xff << 2));
  }
}