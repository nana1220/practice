/*
Given a directed graph, check whether the graph contains a cycle or not. Your function should return true if the given graph contains at least one cycle,
 else return false. For example, the following graph contains three cycles 0->2->0, 0->1->2->0 and 3->3, so your function must return true.


有向图中存在环当且仅当这个图中有back edge。Back edge指的是一条从一个节点到自身或者到他的祖先的edge。下图中有三条back edge（用x表示）。
然后用DFS做就好了。用一个vector或者set来保存当前DFS过程中经过的节点，如果已经经过，则有cycle。
注意每个顶点都要判断一次。
时间复杂度：
如果做一个full DFS, 时间复杂度是O(V^2 + VE)，但是这里我们在碰到之前已经访问过的节点就返回了，所以每个节点只被访问一次，所以最终时间复杂度是O(V + E)。
 */

class SOlu{
  bool has_cycle() {
    vector<bool> visited(_num_verts, false);
    vector<bool> recstack(_num_verts, false);

    function<bool(int)> cycle = [&](int node) {
      if(!visited[node]) {
        visited[node] = recstack[node] = true;

        for(auto adj : _adjs[node]) {
          if (!visited[adj] && cycle(adj)) return true;
          else if(recstack[adj]) return true;
        }
      }

      recstack[node] = false;
      return false;
    };

    for(auto i = 0; i < _num_verts; ++i) {
      if(cycle(i)) return true;
    }
    return false;
  }

}