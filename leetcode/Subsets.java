/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

public class Solution {
  public ArrayList<ArrayList<Integer>> subsets(int[] S) {
    Arrays.sort(S);
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> list = new  ArrayList<Integer>();
    dfs(S, 0, res, list);
    return res;
  }

  void dfs(int[] S, int idx, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list) {
    if(idx == S.length){
      res.add(new ArrayList(list)); // remember new!!!!
      return;
    }
    dfs(S, idx+1, res, list); // item not added
    list.add(S[idx]);
    dfs(S, idx+1, res, list); // item added
    list.remove(list.size()-1);
  }
}