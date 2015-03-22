package basics;

import java.util.Arrays;

public class MergeSort {
  static int[] mergeSort(int[] arr) {
    if (arr.length == 1|| arr.length == 0) { // equal to 0 is error check, only 1 is base case
      return arr;
    }
    int mid = arr.length / 2;
    int[] left = new int[mid];
    int[] right = new int[arr.length - mid];
    System.arraycopy(arr, 0, left, 0, left.length);
    System.arraycopy(arr, mid, right, 0, right.length);
    left = mergeSort(left);
    right = mergeSort(right);
    return merge(arr, left, right);
  }

  static int[] merge(int[] arr, int[] left, int[] right) {
//    int[] res = new int[left.length + right.length]; // no need to create new array, modify input and return
    int leftIdx = 0;
    int rightIdx = 0;
    int resIdx = 0;
    while(leftIdx < left.length && rightIdx < right.length) {
      if (left[leftIdx] < right[rightIdx]) {
        arr[resIdx] = left[leftIdx];
        leftIdx++;
      } else {
        arr[resIdx] = right[rightIdx];
        rightIdx++;
      }
      resIdx++;
    }
    if (leftIdx < left.length)
      System.arraycopy(left, leftIdx, arr, resIdx, arr.length - resIdx); // Note: arraycopy not arrayCopy
    if (rightIdx < right.length)
      System.arraycopy(right, rightIdx, arr, resIdx, arr.length - resIdx);
    return arr;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(mergeSort(new int[]{3, 2, 7, 5, 4, 9, 0, 8, 1, 6})));
  }
}
