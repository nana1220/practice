package ch9recursionanddynamicprogramming;

import java.lang.Math;

/*
 *  A magic index in an array A[1.. .n-1] is defined to be an index such that A[i] = i.
 *  Given a sorted array of distinct integers, write a method to find a magic index, if
 *  one exists, in array A.
 *  FOLLOW UP
 *  What if the values are not distinct?
 */
public class MagicIndex {
  /*
   * distinct values in array, index can only increase one by one, once arr[idx] > idx
   * arr[idx] can never be equal to idx, because arr[idx] increase at least by one
   * time: worst case O(n)
   */
  static int getIndex1(int[] arr, int idx) {
    if (idx >= arr.length || arr[idx] > idx) {
      return -1;
    }
    if (arr[idx] == idx) {
      return idx;
    }
    return getIndex1(arr, idx + 1);
  }

  /*
   * values not distinct
   */
  static int getIndex2(int[] arr, int idx) {
    if (idx >= arr.length) {
      return -1;
    }
    if (arr[idx] == idx) {
      return idx;
    }
    // because duplicated values are allowed, arr[idx] can stay same waiting for index to increase
    int nextPossilbeIdx = Math.max(arr[idx], idx + 1);
    return getIndex2(arr, nextPossilbeIdx);
  }

  /*
   * distinct values
   * because array is sorted, use BST to find
   */
  static int getIndexBst1(int[] arr, int first, int last) {
    if (first > last) return -1;
    int mid = (first + last) / 2;
    if (arr[mid] == mid) {
      return mid;
    }
    if (arr[mid] > mid) {
      return getIndexBst1(arr, first, mid - 1);
    } else {
      return getIndexBst1(arr, mid + 1, last);
    }
  }

  /*
   * duplicated values, if one side cannot find result, recurse another side
   */
  static int getIndexBst2(int[] arr, int first, int last) {
    if (first > last || first < 0 || last >= arr.length) return -1;
    int mid = (first + last) / 2;
    if (arr[mid] == mid) {
      return mid;
    }
    int res;
    if (arr[mid] > mid) {
      res = getIndexBst2(arr, first, mid - 1);
      if (res == -1) res = getIndexBst2(arr, arr[mid], last);
      return res;
    }
    res = getIndexBst2(arr, mid + 1, last);
    if (res == -1) res = getIndexBst2(arr, first, arr[mid]);
    return res;
  }

  public static void main(String[] args) {
    int[] arr1 = new int[] {-1, 0, 2, 5};
    int[] arr2 = new int[] {-1, 0, 3, 3, 5};
    System.out.println(getIndex1(arr1, 0));
    System.out.println(getIndex2(arr2, 0));
    System.out.println(getIndexBst1(arr1, 0, 4));
    System.out.println(getIndexBst2(arr2, 0, 5));
  }
}