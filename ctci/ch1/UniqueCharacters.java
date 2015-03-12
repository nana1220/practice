import java.util.Arrays;

public class UniqueCharacters {
  public static void main(String[] args) {
    for (String arg : args) {
      System.out.println(checkUnique1(arg));
      System.out.println(checkUnique2(arg));
      System.out.println(checkUnique3(arg));
    }
  }

  /*
   * traverse every element
   * time: O(n^2); space: O(1)
   */
  static boolean checkUnique1(String str) {
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      for (int j = i + 1; j < str.length(); j++) {
        if (ch != str.charAt(j)) {
          continue;
        }
        return false;
      }
    }
    return true;
  }

  /*
   * sort first, then check
   * time: O(nlog(n)); space O(n)
   */
  static boolean checkUnique2(String str) {
    char[] arr = str.toCharArray();
    Arrays.sort(arr);
    for (int i = 0; i < str.length() - 1; i++) {
      if (arr[i] == arr[i + 1]) {
        return false;
      }
    }
    return true;
  }

  /*
   * use boolean array marking the appearance of chars (ACII 256 or Unicode, UTF8 2^8, UTF16 2^16)
   * time: O(n); space: O(1)
   */
  static boolean checkUnique3(String str) {
    if (str.length() > 256) { // string length is longer than number of different characters
      return false;
    }
    boolean[] marker = new boolean[256];
    for (int i = 0; i < str.length(); i++) {
      int val = str.charAt(i);
      if (marker[val]) {
        return false;
      }
      marker[val] = true;
    }
    return true;
  }
}