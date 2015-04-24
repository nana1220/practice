/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
 */

// can use an additional hashset to store result to check duplication
public class Solution {
  public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(num);
    Integer previ = null;
    for (int i = 0; i < num.length; i++) {
      int curri;
      if (previ == null) curri = num[i];
      else {
        while (i < num.length && num[i] == previ) i++;
        if (i == num.length) break;
        else curri = num[i];
      }
      previ = curri;
      Integer prevj = null;
      for (int j = i + 1; j < num.length; j++) {
        if (prevj != null) {
          while (j < num.length && num[j] == prevj) j++;
          if (j == num.length) break;
        }
        int currj = num[j];
        prevj = currj;
        int left = j + 1;
        int right = num.length - 1;
        while (left < right) {
          int sum = num[left] + num[right] + curri + currj;
          if (sum == target) {
            ArrayList<Integer> quad = new ArrayList<Integer>();
            quad.add(curri);
            quad.add(currj);
            quad.add(num[left]);
            quad.add(num[right]);
            res.add(quad);
            do {
              left++;
            } while (left < right && num[left] == num[left - 1]);
            do {
              right--;
            } while (left < right && num[right] == num[right + 1]);
          } else if (sum < target) {
            do {
              left++;
            } while (left < right && num[left] == num[left - 1]);
          } else {
            do {
              right--;
            } while (left < right && num[right] == num[right + 1]);
          }

        }
      }
    }
    return res;

  }

  HashMap<Integer, Integer> removeDup(int[] num) { // sorted num
    HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
    int pos = 0;
    res.put(num[0], pos++);
    for (int i = 0; i < num.length; ) {
      int j = i + 1;
      while (j < num.length && num[j] == num[i]) {
        j++;
      }
      if (j != num.length) {
        res.put(num[j], pos++);
      }
      i = j;
    }
    return res;
  }
}

public class Solution {
  public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(num);
    Integer previ = null;
    for (int i = 0; i < num.length; i++) {
      int curri;
      if (previ == null) curri = num[i];
      else {
        while (i < num.length && num[i] == previ) i++;
        if (i == num.length) break;
        else curri = num[i];
      }
      previ = curri;
      Integer prevj = null;
      for (int j = i + 1; j < num.length; j++) {
        if (prevj != null) {
          while (j < num.length && num[j] == prevj) j++;
          if (j == num.length) break;
        }
        int currj = num[j];
        prevj = currj;
        int left = j + 1;
        int right = num.length - 1;
        while (left < right) {
          int sum = num[left] + num[right] + curri + currj;
          if (sum == target) {
            ArrayList<Integer> quad = new ArrayList<Integer>();
            quad.add(curri);
            quad.add(currj);
            quad.add(num[left]);
            quad.add(num[right]);
            res.add(quad);
            do {
              left++;
            } while (left < right && num[left] == num[left - 1]);
            do {
              right--;
            } while (left < right && num[right] == num[right + 1]);
          } else if (sum < target) {
            do {
              left++;
            } while (left < right && num[left] == num[left - 1]);
          } else {
            do {
              right--;
            } while (left < right && num[right] == num[right + 1]);
          }

        }
      }
    }
    return res;

  }
  // this method not used
  HashMap<Integer, Integer> removeDup(int[] num) { // sorted num
    HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
    int pos = 0;
    res.put(num[0], pos++);
    for (int i = 0; i < num.length; ) {
      int j = i + 1;
      while (j < num.length && num[j] == num[i]) {
        j++;
      }
      if (j != num.length) {
        res.put(num[j], pos++);
      }
      i = j;
    }
    return res;
  }
}