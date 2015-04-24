/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

public class Solution {
  public ArrayList<ArrayList<Integer>> combine(int n, int k) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    dfs(n,k,1,res,list);
    return res;
  }
  // use idx to indicate the lower bound of the value that can be picked
  void dfs(int n, int k, int idx, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list) {
    if(list.size() == k){
      res.add(new ArrayList(list));
      return;
    }
    for(int i=idx; i<=n; i++) {
      list.add(i);
      dfs(n, k, i+1, res, list);
      list.remove(list.size()-1);
    }
  }
}