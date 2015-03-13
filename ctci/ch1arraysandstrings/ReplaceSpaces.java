package ch1arraysandstrings;

import java.lang.String;
import java.lang.System;

public class ReplaceSpaces {
  public static void main(String[] args) {
    char[] arg = args[0].toCharArray();
    replaceSpaces(arg);
    System.out.println(arg);
  }

  /*
   * This is a in-place replacement and there are enough buffer at the end of string.
   * Work from end backward to front, so that overwriting usefull chars can be avoided.
   * If the buffer at the end is longer than the space '%20' would need, end position
   * must go backward first, otherwise some characters will still remain at the front
   * after replacement is done.
   */
  static void replaceSpaces(char[] str) {
    int endPos = str.length - 1;
    int curPos = str.length - 1;
    int countBuffer = 0;
    for (; curPos > -1; curPos--) {
      if (str[curPos] != ' ') {
        break; // find the position of last non-white spaces character
      }
      countBuffer++;
    }
    int count = 0; // number of spaces that will be replaced
    for (int i = curPos; i > -1 ; i--) {
      if (str[i] == ' ') {
        count++;
      }
    }
    int extraBuffer = countBuffer - 2 * count; // each space needs 2 spaces of buffer
    endPos -= extraBuffer;
    for (; curPos > -1; curPos--) {
      if (str[curPos] != ' ') {
        str[endPos--] = str[curPos];
      } else {
        str[endPos--] = '0';
        str[endPos--] = '2';
        str[endPos--] = '%';
      }
    }
  }
}