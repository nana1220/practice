/*
Given an array
Indexes 0 1 2 3 4
Values 3 2 1 4 0
Values are the next index of the jump 0 -> 3 -> 4 -> 0... 1 -> 2 -> 1...
Write a function to detect if there are loops. If yes, get the max length of the loop possible, otherwise just return zero.
 */

// 实际上就是有向图找最大环。 用一个DFS走一遍就好，用两个数组，一个记录visited，一个记录走过的长度。
class Solu{
  int max_length(const vector<int>& arr) {
    int ret = 0;
    vector<bool> visited(arr.size(), false);
    vector<int> length(arr.size(), 0);

    function<void(int,int)> dfs = [&] (int id, int len) {
      if(visited[id]) {
        ret = std::max(ret, len - length[id]); // second time reaching this index, currentSteps - storedSteps is the length of loop
        return;
      }
      visited[id] = true;
      length[id] = len; // store the num of steps the first time reaching this index
      dfs(arr[id], len + 1);
    };

    for(int i = 0; i < arr.size(); ++i) dfs(i,0);
    return ret >= 2 ? ret : 0;
  }
}