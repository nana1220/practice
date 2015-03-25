/*
 Design an algorithm to find all pairs of integers within an array which sum to a spec-
ified value
 */

package ch17moderate;

import java.util.Arrays;
import java.util.HashMap;

public class IntegerPairSum {
  /*
   * go one path, put each value into a hash map
   */
  static void find1(int sum, int[] arr, HashMap<Integer, Integer> map) {
    for (int i = 0; i < arr.length; i++) {
      if (map.containsKey(sum - arr[i])) map.put(sum - arr[i], arr[i]);
      else map.put(arr[i], Integer.MAX_VALUE);
    }
  }

  /*
   * sort first, then shrink from both ends
   */
  static void find2(int sum, int[] arr, HashMap<Integer, Integer> map) {
    Arrays.sort(arr);
    int first = 0;
    int last = arr.length - 1;
    while (first < last) {
      while (arr[first] + arr[last] > sum) {
        last--;
      }
      if (first == last) break; // Note: need to add this guard!!!!
      if (arr[first] + arr[last] == sum) map.put(arr[first], arr[last]);
      first++;
    }
  }

  /*
   * more clear than find2
   */
  static void find3(int sum, int[] arr, HashMap<Integer, Integer> map) {
    Arrays.sort(arr); // Note: Can use util class to sort array!! Don't need to write from scratch.
    int first = 0;
    int last = arr.length - 1;
    while (first < last) {
      int s = arr[first] + arr[last];
      if (s == sum) {
        map.put(arr[first], arr[last]);
        first++;
        last--;
      } else {
        if (s > sum) last--;
        else first++;
      }
    }
  }
  public static void main(String[] args) {
    int sum = 12;
    int[] test = {9, 3, 6, 5, 7, -1, 13, 14, -2, 12, 0};
    HashMap<Integer, Integer> res1 = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> res2 = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> res3 = new HashMap<Integer, Integer>();
    find1(sum, test, res1);
    find2(sum, test, res2);
    find3(sum, test, res3);
    for (Integer i : res1.keySet()) {
      System.out.println(i + " " + res1.get(i));
    }
    System.out.println();
    for (Integer i : res2.keySet()) {
      System.out.println(i + " " + res2.get(i));
    }
    System.out.println();
    for (Integer i : res3.keySet()) {
      System.out.println(i + " " + res3.get(i));
    }
  }
}