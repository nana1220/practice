/*
There are n nodes numbered from 0 to n-1 and a set of edges (undirected).
Please determine if it is a valid tree.

For example:
n = 5, edge set = {{0,1}, {0,2}, {2,3}, {2,4}}
Result: true

n = 5, edge set = {{0,1}, {1,2}, {0,2}, {2,3}, {2,4}}
Result: false

n = 4, edge set = {{0,1}, {2,3}}
Result: false
 */

/*
可以用dfs判断联通和找环，也可以用并查集来做。
并查集的理解思路相对简单一些，首先初始化一个长度为n的并查集，遍历所有edge，首先find这个edge的两个节点，如果已经有同一个祖先
则表明存在环，也就不可能是树。构建并查集之后，再扫一遍找出祖先的数量即可，超过一个就不是树。
注意，这里是无向图，对有向图来说，需要判断所有节点的入度是否大于1。可以用一个入度的数组来保存。
 */

// 并查集代码
class UnionFind {
  vector<int> father, ranks;
  int nums{ 0 };
  public:
  UnionFind(int num_nodes) : nums(num_nodes) {
    for (int i = 0; i < num_nodes; i++) { // initialize to n disjoint set each containing one node with rank 0
      father.push_back(i);
      ranks.push_back(0);
    }
  }
  int find(int x) {
    if (x == father[x]) return x;
    return father[x] = find(father[x]);
  }
  void do_union(int x, int y) {
    x = find(x);
    y = find(y);
    if (x == y) return;
    if (ranks[x] < ranks[y]) {
      father[x] = y;
    } else {
      father[y] = x;
      if (ranks[x] == ranks[y]) {
        ranks[x]++;
      }
    }
  }
  int num_sets() {
    int count = 0;
    for(int i = 0; i < nums; ++i) {
      if(father[i] == i) count++;
    }
    return count;
  }
};
  bool valid_tree(int n, const set<pair<int,int>>& edges) {
    if(!n || edges.empty()) return false;

    UnionFind u(n);
    for(auto& p : edges) {
      if(u.find(p.first) == u.find(p.second)) {
        return false;
      }
      u.do_union(p.first, p.second);
    }
    return u.num_sets() == 1;
  }


/*
普通的图算法来做，也就是判断有没有环以及是不是强连通
 */
public class Solution {
  // detect cycle for undirected grapth
  bool solve(vector<vector<int>>&edges, int n, int parent, unordered_set<int>&visited, unordered_set<int>&path) {
    if (path.count(n)) return false; // detect cycle, node already in the path, means has cycle
    path.insert(n);
    visited.insert(n);
    for (int i = 0; i < edges[n].size(); i++) { // iterate node n's neighbors, excluding its parent, since undirected grapgh, parent must be one of neighbors
      if (parent != edges[n][i] && !solve(edges, edges[n][i], n, visited, path)) {
        return false;
      }
    }
    path.erase(n);
    return true; // no cycle
  }

  bool valid_tree(vector<vector<int>>&edges, int n) {
    vector<vector<int>> neighbors (n); // convert edges to each node's adjacent set
    for (int i = 0; i < edges.size(); i++) {
      neighbors[edges[i][0]].push_back(edges[i][1]);
      neighbors[edges[i][1]].push_back(edges[i][0]);
    }

    unordered_set<int> visited, path;
    if (!solve(neighbors, 0, -1, visited, path)) return false; // if has cycle return false
    for (int i = 0; i < n; i++) {
      if (!visited.count(i)) return false; // check if every node has been visited, if not return false
    }
    return true;
  }
}

class soluCycle{
  // A recursive function that uses visited[] and parent to detect
// cycle in subgraph reachable from vertex v.
  bool Graph::isCyclicUtil(int v, bool visited[], int parent)
  {
    // Mark the current node as visited
    visited[v] = true;

    // Recur for all the vertices adjacent to this vertex
    list<int>::iterator i;
    for (i = adj[v].begin(); i != adj[v].end(); ++i)
    {
      // If an adjacent is not visited, then recur for that adjacent
      if (!visited[*i])
      {
        if (isCyclicUtil(*i, visited, v))
        return true;
      }

      // If an adjacent is visited and not parent of current vertex,
      // then there is a cycle.
      else if (*i != parent)
      return true;
    }
    return false;
  }

  // Returns true if the graph contains a cycle, else false.
  bool Graph::isCyclic()
  {
    // Mark all the vertices as not visited and not part of recursion
    // stack
    bool *visited = new bool[V];
    for (int i = 0; i < V; i++)
      visited[i] = false;

    // Call the recursive helper function to detect cycle in different
    // DFS trees
    for (int u = 0; u < V; u++)
      if (!visited[u]) // Don't recur for u if it is already visited
        if (isCyclicUtil(u, visited, -1))
          return true;

    return false;
  }
}