/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8,
A solution set is:
[1, 7]
[1, 2, 5]
[2, 6]
[1, 1, 6]
 */

public class Solution {
  public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
    Arrays.sort(num);
    compute(num, target, 0, list, res, set);
    return res;
  }
  void compute(int[] num, int sum, int idx, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res, HashSet<ArrayList<Integer>> set){
    if (sum==0) {
      ArrayList<Integer> r = new ArrayList<Integer>(list);
      if(!set.contains(r)){
        res.add(r);
        set.add(r);
      }
      return;
    }
    if (idx == num.length) return; // Note: CombinationSum pass i instead of i+1, so does't have this condition
    if (sum < 0) return;

    for (int i =idx; i< num.length ; i++){
      list.add(num[i]);
      compute(num, sum -num[i], i + 1, list, res, set); // Note: i+1, next recurrsion can not pick current item
      list.remove(list.size()-1);
    }
  }
}