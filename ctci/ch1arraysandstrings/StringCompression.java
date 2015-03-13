package ch1arraysandstrings;

import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class StringCompression {

  public static void main(String[] args) {
    System.out.println(compress1(args[0]));
    System.out.println(compress2(args[0]));
  }

  static String compress1(String str) {
    StringBuilder compressedStr = new StringBuilder();
    int count = 1;
    for (int i = 0; i < str.length() - 1; i++) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        count++;
      } else {
        compressedStr.append(str.charAt(i));
        compressedStr.append(count);
        count = 1;
      }
    }
    compressedStr.append(str.charAt(str.length() - 1));
    compressedStr.append(count);
    if (compressedStr.length() > str.length()) {
      return str;
    }
    return compressedStr.toString();
  }

  /*
   * compute compression size first, then use array instead of StringBuilder
   */
  static String compress2(String str) {
    int size = 0;
    int count = 1;
    for (int i = 0; i < str.length() - 1; i++) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        count++;
      } else {
        // convert count to String whose length is equal to the number of digit of count
        size += (1 + String.valueOf(count).length());
        count = 1;
      }
    }
    size += (1 + String.valueOf(count).length());
    if (size > str.length()) {
      return str;
    }
    char[] compressedStr = new char[size];
    int index = 0;
    count = 1;
    for (int i = 0; i < str.length() - 1; i++) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        count++;
      } else {
        compressedStr[index++] = str.charAt(i);
        String countStr = String.valueOf(count);
        for (int j = 0; j < countStr.length(); j++) {
          compressedStr[index++] = countStr.charAt(j);
        }
        count = 1;
      }
    }
    compressedStr[index++] = str.charAt(str.length() - 1);
    String countStr = String.valueOf(count);
    for (int j = 0; j < countStr.length(); j++) {
      compressedStr[index++] = countStr.charAt(j);
    }
    return String.valueOf(compressedStr);
  }
}