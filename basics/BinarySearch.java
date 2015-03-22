package basics;

public class BinarySearch {
  static int search(int[] arr, int x) {
    int start = 0;
    int end = arr.length - 1; // end is inclusive
    int mid;
    while (start <= end) { // Note: this is the condition
      mid = (start + end) / 2;
      if (x < arr[mid]) {
        end = mid - 1; // note: how to change end, don't simply set end = mid, otherwise cannot lead to start > end
      } else if (x > arr[mid]) {
        start = mid + 1; // note: how to change start, don't simply set start = mid
      } else {
        return mid;
      }
    }
    return -1;
  }

  static int searchRecur(int[] arr, int x, int start, int end) { // end is inclusive
    if (start > end) { // return closest
      if (end < 0) return start;
      if (start >= arr.length) return end;
      if (x - arr[end] < arr[start] - x) {
        return end;
      }
      return start;
    }
    int mid = (start + end) / 2;
    if (x < arr[mid]) {
      return searchRecur(arr, x, start, mid - 1);
    } else if (x > arr[mid]) {
      return searchRecur(arr, x, mid + 1, end);
    } else {
      return mid;
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[]{3, 2, 7, 5, 4, 9, 0, 8, 1, 6};
    System.out.println(search(arr, 2));
    System.out.println(searchRecur(arr, 2, 0, 9));
  }
}