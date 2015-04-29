/*
Print all unique combination of factors (except 1) of a given number.
For example:
Input: 12
Output: [[2, 2, 3], [2, 6], [3, 4]]
Input: 15
Output: [[3, 5]]
Input: 28
Output: [[2, 2, 7], [2, 14], [4, 7]]
 */

// 典型DFS做法，注意避免重复。

public class Solu{
  vector<vector<int>> factors_comb(int n) {
    vector<vector<int>> ret = {};
    function<void(int, vector<int>&)> dfs = [&](int num, vector<int>& cur) {
      int last = cur.empty() ? 2 : cur.back();
      // starting from last factor to avoid dup, e.g. 2*2*7, second recursion starts from 2, 3rd recursion starts from 2, 4th starts from 7x
      for(int f = last; f < num; ++f) {
        if(num % f != 0) continue;
        cur.push_back(f);
        dfs(num / f, cur);
        cur.pop_back();
      }
      if(!cur.empty() && num >= last) { // termination condition is num >= last, then put num in result
        cur.push_back(num);
        ret.push_back(cur);
        cur.pop_back();
      }
    };
    vector<int> cur = {};
    dfs(n, cur);
    return ret;
  }
}