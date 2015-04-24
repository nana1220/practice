/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3]
 */

public class Solution {
  public ArrayList<LinkedList<Integer>> combinationSum(int[] candidates, int target) {
    ArrayList<LinkedList<Integer>> res = new ArrayList<LinkedList<Integer>>();
    LinkedList<Integer> list = new LinkedList<Integer>();
    Arrays.sort(candidates); // start from largest item in candidates
    compute(candidates, candidates.length -1, target, list, res);
    return res;
  }

  void compute(int[] sortedCand, int idx, int sum, LinkedList<Integer> list, ArrayList<LinkedList<Integer>> res) {
    if (sum ==0) {res.add(new LinkedList(list)); return;} // remember to add a clone to res
    if(idx ==0) {
      if (sum == sum / sortedCand[0] * sortedCand[0]) {
        int num = sum/sortedCand[0];
        while(num>0){
          list.addFirst(sortedCand[0]);
          num--;
        }
        res.add(new LinkedList(list));
        num = sum/sortedCand[0];
        while(num>0){
          list.poll();
          num--;
        }
      }
      return;
    }
    while (sum >= 0){ // Note!!: >= not just >
      compute(sortedCand, idx -1, sum, list, res); // from not inlucding current item to including one, and two ...
      sum -= sortedCand[idx];
      list.addFirst(sortedCand[idx]);
    }
    // Remember to remove items before return
    while(!list.isEmpty() && list.peek() == sortedCand[idx]) list.poll(); // Remeber to check empty first!!
  }

  // a neater solution, call j at 0
  public void combinationSum(int[] candidates, int target, int j,
                             ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> result){
    if(target == 0){
      ArrayList<Integer> temp = new ArrayList<Integer>(curr);
      result.add(temp);
      return;
    }
    for(int i=j; i<candidates.length; i++){
      if(target < candidates[i])
        return;
      curr.add(candidates[i]);
      // Note here, I add same item with different numbers at one recurssion
      //  solution add onlyw one item each recursion
      combinationSum(candidates, target - candidates[i], i, curr, result);
      curr.remove(curr.size()-1);
    }
  }

}