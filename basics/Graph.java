class Graph {
  public:
  Graph(int numVert) :_num_verts(numVert) {
    assert(numVert > 0);
    _adjs = new unordered_set<int>[numVert];
  }
  ~Graph() {
    if (_adjs) {
      delete[] _adjs;
      _adjs = nullptr;
    }
  }
  void add_edge(int v, int w) {
    assert(v >= 0 && v < _num_verts);
    assert(w >= 0 && w < _num_verts);
    _adjs[v].insert(w);
  }

  vector<int> dfs_traversal(int start) {
    set<int> visited;
    vector<int> ret;

    function<void(int)> dfs = [&](int node) {
      if(node >= _num_verts || visited.count(node)) return;

      visited.insert(node);
      ret.push_back(node);

      for(auto n : _adjs[node]) {
        dfs(n);
      }
    };

    dfs(start);
    return ret;
  }

  vector<int> bfs_traversal(int start) {
    vector<int> ret;
    if(start >= _num_verts) return ret;

    set<int> visited;
    queue<int> q;
    q.push(start);

    while(!q.empty()) {
      int cur_node = q.front();
      q.pop();

      if(visited.count(cur_node)) continue;
      visited.insert(cur_node);

      ret.push_back(cur_node);

      for(auto nd : _adjs[cur_node]) {
        q.push(nd);
      }
    }
    return ret;
  }

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
  vector<int> topo_sort() {
    vector<int> ret;
    vector<bool> visited(_num_verts, false);
    function<void(int)> visit = [&](int v){
      visited[v] = true;
      for(auto adj : _adjs[v]) {
        if(!visited[adj]) visit(adj);
      }
      ret.push_back(v);
    };
    for(auto i = 0; i < _num_verts; ++i) {
      if(!visited[i]) visit(i);
    }
    return ret;
  }

  Graph* get_transpose() {
    Graph* g = new Graph(_num_verts);
    for(auto i = 0; i < _num_verts; ++i) {
      for(auto adj : _adjs[i]) {
        g->_adjs[adj].insert(i);
      }
    }
    return g;
  }

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
      visited[i] = false;
    }

    auto g = get_transpose();
    dfs(g, 0);
    delete g;

    for (int i = 0; i < _num_verts; i++)
      if (!visited[i]) return false;

    return true;
  }
  private:
  int _num_verts{ 0 };
  unordered_set<int>* _adjs{ nullptr };
};
