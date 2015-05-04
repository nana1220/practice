/*
Given a directed graph, find out whether the graph is strongly connected or not. A directed graph is strongly connected if there is a path between
any two pair of vertices. For example, following is a strongly connected graph.


对于无向图来说判断连通非常简单，只需要做一次搜索，然后判断路径中是否经过了每个节点即可。但是有向图不可以这么做，比如上图，从节点0开始搜索可以经过所有节点，
但是很明显它不是strongly connected, bixu两两huxiang可达.


Naive的方法是，对每一个节点做一次DFS，如果存在一个节点在DFS中没有经过每一个节点，那么则不是强连通图。这个算法复杂度是O(V*(V+E))。
或者用Floyd Warshall算法来找出任意两个节点的最短路径。复杂度是O(v3)。

一个更好的办法是强连通分量算法Strongly Connected Components (SCC) algorithm。我们可以用O(V+E)时间复杂度找出一个图中所有的SCC。如果SCC只有一个，那么这个图就是强连通图。
*/

/*
用Kosaraju算法，两个pass做DFS：

1 开一个visited数组，标记所有点为unvisited.
2 从任意顶点V走一次DFS，如果没有访问到所有顶点则返回false。
3 将所有边reverse。, use clone graph to reverse edge, HasmMap<origNode, reverseNode>;
把reverse后的图的所有定点重新标记为unvisited。
4 继续对新图走一次DFS，起点跟2中的顶点V。如果DFS没有访问到所有点则返回false，否则返回true。
5 算法的整体思路是，如果一个点可以访问到其他所有点，同时其他所有点可以访问到这个点，那么这个图就是强连通图。时间复杂度为O(V+E)
 */

class solu{
  bool strongly_connected() {
    vector<bool> visited(_num_verts, false);
    function<void(const Graph*, int)> dfs = [&](const Graph* g, int node) {
      visited[node] = true;
      for(auto adj : g->_adjs[node]) {
        if (!visited[adj]) dfs(g, adj);
      }
    };
    dfs(this, 0);
    for (int i = 0; i < _num_verts; i++) {
      if (!visited[i]) return false;
      visited[i] = false; // reset visited
    }
    auto g = get_transpose();// reverse all edges
    dfs(g, 0);
    delete g;
    for (int i = 0; i < _num_verts; i++)
      if (!visited[i]) return false;
    return true;
  }
}