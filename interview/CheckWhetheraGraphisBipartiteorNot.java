/*
A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such that every edge (u, v)
 either connects a vertex from U to V or a vertex from V to U. In other words, for every edge (u, v), either u belongs to U
 and v to V, or u belongs to V and v to U. We can also say that there is no edge that connects vertices of same set.
*/


/*
Assign RED color to the source vertex (putting into set U).
Color all the neighbors with BLUE color (putting into set V).
Color all neighbor’s neighbor with RED color (putting into set U).
This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot
 be colored with 2 vertices (or graph is not Bipartite)
Time Complexity of the above approach is same as that Breadth First Search. In above implementation is O(V^2) where V is
 number of vertices. If graph is represented using adjacency list, then the complexity becomes O(V+E).
 */

class SOlu{
  bool is_bipartite(int src) {
    const int RED = 1, BLUE = 2, UNCOLORED = -1;
    vector<int> colors(_num_verts, UNCOLORED);
    colors[src] = RED;
    queue <int> q;
    q.push(src);
    while(!q.empty()) {
      int node = q.front();
      q.pop();
      for(auto neig : _adjs[node]) {
        if(colors[neig] == UNCOLORED) {
          colors[neig] = colors[node] == RED ? BLUE : RED;
          q.push(neig);
        } else if(colors[neig] == colors[node]) {
          return false;
        }
      }
    }
    return true;
  }
}