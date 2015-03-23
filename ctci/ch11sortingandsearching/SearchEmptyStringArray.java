package ch11sortingandsearching;

/*
 * Given a sorted array of strings which is interspersed with empty strings, write a
 * method to find the location of a given string.
 */

public class SearchEmptyStringArray {
  static int search(String[] strings, String x, int start, int end) {
    if (start < end) {
      return -1;
    }
    int mid = (start + end) / 2;
    if (strings[mid].equals(x)) {
      return mid;
    }
    if (strings[mid].isEmpty()) {
      int left = mid - 1;
      int right = mid + 1;
      while (true) {
        if (left < start && right > end) {
          return -1;
        }
        if (left >= start && !strings[left].isEmpty()) { // Note: bound checking, if not out of bound and non-empty
          mid = left;
          break;
        }
        if (right <= end && !strings[right].isEmpty()) {
          mid = right;
          break;
        }
        left--;
        right++;
      }
    }
    if (strings[mid].compareTo(x) < 0) {
      return search(strings, x, mid + 1, end);
    } else {
      return search(strings, x, start, mid - 1);
    }
  }
}