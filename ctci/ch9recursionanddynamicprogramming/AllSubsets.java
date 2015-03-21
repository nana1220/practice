package ch9recursionanddynamicprogramming;

import java.lang.Integer;
import java.util.ArrayList;

/*
 *  Write a method to return all subsets of a set.
 */
public class AllSubsets {

  /*
   * use n digits binary number represents each subset, if i-th digit is 0, then
   * i-th element in the set doesn't appear in this subset, while 1 means appearance
   */
  static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    int n = set.size();
    // Note: a better way computing 2^n is 1 << n; int maxNumber = 1 << n;
    for (int binaryNumber = 0; binaryNumber < (int) Math.pow(2, n); binaryNumber++) {
      ArrayList<Integer> subset = new ArrayList<Integer>();
      // Note: this a better way to shift and take least siganificant digit
      int temp = binaryNumber;
      int index = 0;
      while (temp != 0) {
        if ((temp & 1) == 1) { // Note: (temp & 1) needs bracket
          subset.add(set.get(index));
        }
        temp = temp >> 1;
        index++;
      }
      // This is not good,
//      for (int i = 0; i < n; i++) { // shift each bit of binary number one by one
//        if (((binaryNumber >> i) & 1) == 1) {
//          subset.add(set.get(i));
//        }
//      }
      res.add(subset);
    }
    return res;
  }

  /*
   * recursion approache
   * all subsets of n elements taken from set are equal to all subsets of n-1 elements taken from set
   * plus put n-th element into each of these subsets
   */
  static ArrayList<ArrayList<Integer>> getSubsetsRecur(ArrayList<Integer> set, int index) { // element index
    ArrayList<ArrayList<Integer>> subsets;
    if (index == -1) { // base case: index for set is -1, which means don't take any element from set
      subsets = new ArrayList<ArrayList<Integer>>();
      ArrayList<Integer> subset = new ArrayList<Integer>();
      subsets.add(subset);
      return subsets;
    }
    subsets = getSubsetsRecur(set, index - 1);
    ArrayList<ArrayList<Integer>> cache = new ArrayList<ArrayList<Integer>>();
    for (ArrayList<Integer> subset : subsets) {
      ArrayList<Integer> temp = new ArrayList<>();
      temp.addAll(subset);
      temp.add(set.get(index));
      cache.add(temp);
    }
    subsets.addAll(cache);
    return subsets;
  }

  public static void main(String[] args) {
    ArrayList<Integer> set = new ArrayList<Integer>();
    for (int i = 0; i < 3; i++) {
      set.add(i);
    }
    ArrayList<ArrayList<Integer>> subsets = getSubsets(set);
    System.out.println(subsets.toString());

    ArrayList<ArrayList<Integer>> subsets2 = getSubsetsRecur(set, 2);
    System.out.println(subsets2.toString());
  }
}