package ch11sortingandsearching;

/*
 Given a sorted array of n integers that has been rotated an unknown number of
 times, write code to find an element in the array. You may assume that the array was
 originally sorted in increasing order.
 */

/*
 * Either the left or right half must be normally ordered. We can look at the normally ordered half
 * to make a determination as to which half we should search.
 * time: O(log(n)) if all elements are unique, O(n) if too many duplicates because need to always
 * search both sides which means search every elements
 */
public class SearchRotatedArray {

  // TODO: this one has bug
  static int search(int[] arr, int x, int start, int end) {
    if (start > end) return -1;
    int mid = (start + end) / 2;
    if (arr[mid] == x) return mid;
    // one of side is all repeats but can't know which side
    // need to search both sides
    if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
      int res = search(arr, x, start + 1, mid - 1);
      if (res != -1) return res;
      else return search(arr, x, mid + 1, end - 1);
    } else if (arr[start] == arr[mid]) { // left side is all repeated search right side
      return search(arr, x, mid + 1, end);
    } else { // left side is not all repeated, right side is either repeated or not repeated
      if(arr[start] > arr[mid]) { // right side ordered
        if(arr[mid] > x) return search(arr, x, start, mid - 1);
        else if (arr[end] >= x) return search(arr, x, mid + 1, end);
        else return search(arr, x, start, mid - 1);
      } else { // left side ordered
        if(arr[mid] < x) return search(arr, x, mid + 1, end);
        else if(arr[start] < x) return search(arr, x, start, mid - 1);
        else return search(arr, x, mid + 1, end);
      }
    }
  }

  /*
   * this one works
   */
  public static int search2(int arr[], int x, int left, int right) {
    int mid = (left + right) / 2;
    if (x == arr[mid]) { // Found element
      return mid;
    }
    if (right < left) {
      return -1;
    }
    if (arr[left] < arr[mid]) { // Left is normally ordered.
      if (x >= arr[left] && x <= arr[mid]) {
        return search2(arr, left, mid - 1, x);
      } else {
        return search2(arr, mid + 1, right, x);
      }
    } else if (arr[mid] < arr[left]) { // Right is normally ordered.
      if (x >= arr[mid] && x <= arr[right]) {
        return search2(arr, mid + 1, right, x);
      } else {
        return search2(arr, left, mid - 1, x);
      }
    } else if (arr[left] == arr[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
      if (arr[mid] != arr[right]) { // If right half is different, search there
        return search2(arr, mid + 1, right, x);
      } else { // Else, we have to search both halves
        int result = search2(arr, left, mid - 1, x);
        if (result == -1) {
          return search2(arr, mid + 1, right, x);
        } else {
          return result;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] x = { 3, 4, 5, 6, 2, 2, 2 , 2 , 2 };
    System.out.println(search(x, 3, 0, x.length - 1));
    System.out.println(search(x, 2, 0, x.length - 1));

    System.out.println(search2(x, 3, 0, x.length - 1));
    System.out.println(search2(x, 2, 0, x.length - 1));
  }
}

