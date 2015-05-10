/*
一个数组 A: 1 3 0 2 4 7
input: dest-node: A0
output: all the source nodes: (A1, A3, A4)

数组中每个元素表示他能走的步数，既能向左走 又能向右走，能到A[0]的点有A[1]和A[4]，A[1]可以走3步到A[4] A[4]能走4步道A[0]。
 */

// 反向建图，然后从终点做BFS，路过的点就push到结果里

class Solu{
  vector<int> find_source(const vector<int>& arr, int dest) {

    unordered_map<int,vector<int>> graph;// key is dest, values are its source
    for(int i = 0; i < arr.size(); ++i) {
      if(arr[i] == 0) continue;
      if(i + arr[i] >= 0 && i + arr[i] < arr.size())
        graph[i + arr[i]].push_back(i);
      if(i - arr[i] >= 0 && i - arr[i] < arr.size())
        graph[i - arr[i]].push_back(i);
    }

    vector<int> ret;
    deque<int> q{dest};
    unordered_set<int> visited{dest};

    while(!q.empty()) {
      auto cur = q.front(); q.pop_front();

      for(int i = 0; i < graph[cur].size(); ++i) {
        int next = graph[cur][i];
        if(!visited.count(next)) {
          ret.push_back(next);
          visited.insert(next);
          q.push_back(next);
        }
      }
    }
    return ret;
  }
}