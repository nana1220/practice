import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class RotationString {

  public static void main(String[] args) {

    boolean result = isRotation(args[0], args[1]);
    if (result) {
      System.out.println(args[1] + " is a rotation of " + args[0]);
    } else {
      System.out.println(args[1] + " is not a rotation of " + args[0]);
    }
  }

  /*
   * Str1 being a rotation of str2 is equivalent to str1 being a substring of (str2 + str2).
   * If case insensitive, pass str1.toLowerCase() and str2.toLowerCase().
   */
  public static boolean isRotation(String str1, String str2) {
    if ((str1.length() != str2.length()) || str1.isEmpty() || str2.isEmpty()
        || str1 == null || str2 == null) {
      return false;
    }
    return isSubString(str1, str2 + str2);
    // return isSubString(str1.toLowerCase(), str2.toLowerCase() + str2.toLowerCase());
    // return (str2 + str2).contains(str1);
    // return ((str2 + str2).indexOf(str1) > -1) ? true : false;
  }

  /*
   * Can simply call longStr.contains(shortStr) if shortStr is not null.
   */
  public static boolean isSubString(String shortStr, String longStr) {
    if ((shortStr.length() > longStr.length()) || shortStr.isEmpty() || longStr.isEmpty()) {
      return false;
    }
    for (int i = 0; i < longStr.length() - shortStr.length() + 1; i++) {
      int j = 0;
      for (; j < shortStr.length(); j++) {
        if (shortStr.charAt(j) == longStr.charAt(i + j)) {
          continue;
        } else {
          break;
        }
      }
      if (j == shortStr.length()) {
        return true;
      }
    }
    return false;
  }
}
