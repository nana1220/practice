/*
Given a string, find the longest substring that contains only two unique characters. For example,
given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".
 */

class Solution {
  /*
   two pointers that track the start of the substring and the iteration cursor.
   */
  public static String subString(String s) {
    // checking
    char[] arr = s.toCharArray();
    int max = 0;
    int j = 0; // start
    int m = 0, n = 0; // start and end of current longest string

    HashSet<Character> set = new HashSet<Character>();
    set.add(arr[0]);
    for (int i = 1; i < arr.length; i++) {
      if (set.add(arr[i])) {
        if (set.size() > 2) {
          String str = s.substring(j, i);
          //keep the last character only
          set.clear();
          set.add(arr[i - 1]);
          if ((i - j) > max) {
            m = j;
            n = i - 1;
            max = i - j;
          }
          j = i - helper(str); // shift right until arr[j] = arr[i-1]
        }
      }
    }

    return s.substring(m, n + 1);
  }

  // This method returns the length that contains only one character from right side.
  public static int helper(String str) {
    // null & illegal checking here
    if (str == null) {
      return 0;
    }

    if (str.length() == 1) {
      return 1;
    }

    char[] arr = str.toCharArray();
    char p = arr[arr.length - 1];
    int result = 1;

    for (int i = arr.length - 2; i >= 0; i--) {
      if (p == arr[i]) {
        result++;
      } else {
        break;
      }
    }

    return result;
  }
}