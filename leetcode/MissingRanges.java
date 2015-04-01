/*
Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 */

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
  /*
   * caution!! corner case,
   */
  static public List<String> findMissingRanges1(int[] A, int lower, int upper) {
    List<String> res = new ArrayList<String>();
    int prev = lower - 1;
    for (int i = 0; i < A.length; i++) {
      if(A[i] - prev == 2) {
        String s = (prev + 1) + "";
        res.add(s);
        prev = A[i];
      } else if (A[i] - prev > 2) {
        String s = (prev + 1) + "->" + (A[i] -1);
        res.add(s);
        prev = A[i]; // NOTE: update position
      } else {
        prev++;
      }
    }
    if (A[A.length - 1] != upper) { // NOTE: DON'T FORGET CHECK TAIL!!!!!!!!!!!!
      if (A[A.length - 1] == upper - 1) {
        res.add("" + upper);
      } else {
        res.add((A[A.length - 1] + 1) + "->" + upper);
      }
    }
    return res;
  }

  /*
   * cleaner solution
   */
  public List<String> findMissingRanges2(int[] vals, int lower, int upper) {
    List<String> ranges = new ArrayList<String>();
    int prev = lower - 1;
    for (int i = 0; i <= vals.length; i++) { //  i= length to handle tail
      // This is a better way to handle tail, use upper + 1 check if upper is missing
      int curr = (i == vals.length) ? upper + 1 : vals[i];
      if (curr - prev >= 2)
        ranges.add(getRange(prev + 1, curr - 1));
      prev = curr;
    }
    return ranges;
  }

  private String getRange(int from, int to) {
    return (from == to) ? String.valueOf(from) : from + "->" + to;
  }

  public static void main(String[] args) {
    int[] input = new int[] {0, 1, 2, 50 , 52, 75};
    List<String> res = findMissingRanges1(input, 0, 99);
    for (String s : res) {
      System.out.println(s);
    }
  }


}