package basics;

import java.lang.System;
import java.util.Arrays;

public class QuickSort {

  static int[] quickSort(int[] arr) {
    if (arr.length == 1 || arr.length == 0) return arr; // 0 and 1 are base case
    int pivotIdx = arr.length / 2;
    int pivot = arr[pivotIdx];
    int leftCount = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < pivot)
        leftCount++;
    }
    int[] left = new int[leftCount]; // it’s OK to new int[0] which is equal to new int[]{}, and its length is 0
    int[] right = new int[arr.length - leftCount - 1];
    int leftIdx = 0;
    int rightIdx = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i == pivotIdx) // don't forget skip pivotIdx (pivot value) in the original array
        continue;
      if (arr[i] < pivot) {
        left[leftIdx++] = arr[i];
      } else {
        right[rightIdx++] = arr[i];
      }
    }
    left = quickSort(left);
    right = quickSort(right);
//    int[] res = new int[arr.length]; // Note: no need to create new array can modify input array and return
    System.arraycopy(left, 0, arr, 0, left.length);
    arr[left.length] = pivot;
    System.arraycopy(right, 0, arr, left.length + 1, right.length);
    return arr;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(quickSort(new int[]{3, 2, 7, 5, 4, 9, 0, 8, 1, 6})));
  }
}