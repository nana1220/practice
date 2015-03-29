/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */


public class ImplementstrStr {
  // Brute force
  // length n has (n - m + 1) length m substring, each substring compare m chars
  //O((n-m+1)*m)=O(n*m)
  public int strStr(String haystack, String needle) {
    if (needle.length() > haystack.length()) return -1;
    if (needle.length() == 0) return 0; // NOTE!!: edge case, needle is empty
    int n = haystack.length();
    int m = needle.length();
    for (int i = 0; i < n - m + 1; i++) {
      int j = 0;
      for (; j < m; j++) {
        if (needle.charAt(j) != haystack.charAt(i + j)) {
          break;
        }
      }
      if (j == m) {
        return i;
      }
    }
    return -1;
  }

  /*
   * TODO
   * KMP algorithm
   */
}