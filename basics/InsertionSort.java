package basics;

import java.util.Arrays;

public class InsertionSort {
  static int[] insertionSort(int[] arr) {
    if (arr.length < 2) // don't forget!
      return arr;
    for (int i = 1; i < arr.length; i++) {
      int elemToBeInserted = arr[i];
      for (int j = 0; j < i; j++) {
        if (arr[j] > elemToBeInserted) {
          // copy arr[j,...,i-1] to arr[j+1,...,i] and insert arr[i] to arr[j]
          System.arraycopy(arr, j, arr, j + 1, i - j);
          arr[j] = elemToBeInserted;
          break;
        }
      }
    }
    return arr;
  }
  public static void main(String[] args) {
    System.out.println(Arrays.toString(insertionSort(new int[]{3, 2, 7, 5, 4, 9, 0, 8, 1, 6})));
  }
}