/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

// dfs
// NOTE: this dfs tree here has (length-idx) children at each level, and each node represents a subset, store subset at each node
// The dfs used to solve Subsets.java, each node only has two children, represent including or not including the element denoted by node
// and only store subset when hitting the leaf
public class Solution {
  public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
    ArrayList<ArrayList<Integer>> res =new ArrayList<ArrayList<Integer>>();
    res.add(new ArrayList<Integer>()); // empty set, the root
    ArrayList<Integer> set = new ArrayList<Integer>();
    Arrays.sort(num); // Remeber to sort!!
    dfs(res, set, 0, num);
    return res;
  }
  void dfs(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> set, int idx, int[] num){
    if(idx == num.length) return;
    for(int i=idx; i<num.length; i++){// NOTE: the dfs tree here has (length-idx) children at each level, and each node represents a subset
      if(i>idx && num[i]==num[i-1]) continue; // remove dup
      set.add(num[i]);
      res.add(new ArrayList<Integer>(set)); // add set for each node
      dfs(res, set, i+1, num); //Note : i+1
      set.remove(set.size()-1);
    }
  }
}