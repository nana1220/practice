/*
Describe an algorithm to find the smallest one million numbers in one billion
numbers. Assume that the computer memory can hold all one billion numbers.
 */

package ch18hard;

import java.lang.Override;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * same as k-th smallest elements
 *
 * sort, O(nlgn)
 *
 * maxHeap O(nlgm)
 * Traverse through the list, for each element, we insert it into the max heap and delete the largest
 * element from the heap.
 *
 * quick select: average: O(n)  worst: O(n^2), If the elements are unique, you can find the
 * ith smallest (or largest) element in expected 0(n)
 */

import java.lang.Integer;
import java.util.Random;

public class MSmallestElements {
  /*
   * Maintain a max heap with size m
   * Or put all elements into a min heap and poll first m
   */
  PriorityQueue<Integer> useHeap(int[] arr, int m) {
    // no need to define compare class
    Comparator<Integer> aComp = new Comparator<Integer>() {
      public int compare(Integer a, Integer b) {
        return b - a;
      }
    }; // Don't forget ;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(m, aComp);
    for (int i = 0; i < arr.length; i++) {
      maxHeap.add(arr[i]);
      if (i >= m) {
        maxHeap.poll();
      }
    }
    return maxHeap;
  }

  /*
   * TODO: quick select
   */
  static int quickSelect(int[] arr, int m, int left, int right) { // right is inclusive
    int pivot = arr[left + new Random().nextInt(right - left + 1)];
    // [left, leftEnd), leftEnd is exclusive, left part: elements <= pivot
    int leftEnd = partition(arr, left, right, pivot);
    int leftLengh = leftEnd - left;
    if (leftLengh == m) return max(arr, left, leftEnd - 1);
    else if (leftLengh > m) return quickSelect(arr, m, left, leftEnd - 1);
    else return quickSelect(arr, m - leftLengh, leftEnd, right);
  }

  static int partition(int[] arr, int first, int last, int pivot) {
    while (true) {
      // either first turn bigger than last or arr[first] turn bigger than pivot
      // first always stops on the element which is larger than pivot
      // all the element to the left of first smaller than or equal to pivot
      while (first <= last && arr[first] <= pivot) {
        first++;
      }
      while (first <= last && arr[last] > pivot) {
        last--;
      }
      if (first > last) {
        return first;
      }
      // swap
      int temp = arr[first];
      arr[first] = arr[last];
      arr[last] = temp;
    }
  }

  static int max(int[] arr, int left, int right) {
    int max = Integer.MIN_VALUE;
    for (int i = left; i <= right; i++) {
      max = Math.max(arr[i], max);
    }
    return max;
  }
}

