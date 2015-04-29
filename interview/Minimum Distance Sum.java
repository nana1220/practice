/*
二维矩阵里面有obstacle，已知有k个点，求房间中离这k个点距离之和最短的那个点。


对所有的K个点做一次BFS，记录下每个点的最短距离和，最后扫一遍找出最小值即可。复杂度是O(K*N^2)，感觉有更优的解。
 */

class Solu{
  pair<int,int> min_distance_sum(const vector<string>& mat) {
    if(mat.empty() || mat[0].empty()) return {};
    int m = (int)mat.size(), n = (int)mat[0].size();
    vector<pair<int,int>> pts;
    for(int i = 0; i < m; ++i) {
      for(int j = 0; j < n; ++j) {
        if(mat[i][j] == 'P') pts.push_back({i,j});
      }
    }
    struct Node {
      int dist_sum{ 0 }; // each node store a distance sum to all K nodes
      bool visited{ false };
    };
    vector<vector<Node>> nodes(m, vector<Node>(n));

    for(int p = 0; p < pts.size(); ++p) { // do a BFS for each starting point
      for(int i = 0; i < m; ++i)
        for(int j = 0; j < n; ++j)
          nodes[i][j].visited = false;

      deque<pair<int,int>> q{ pts[p] };
      deque<int> dists{ 0 };
      nodes[pts[p].first][pts[p].second].visited = true;

      while(!q.empty()) {
        auto cur = q.front(); q.pop_front();
        auto dis = dists.front(); dists.pop_front();

        vector<pair<int,int>> dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for(auto d : dirs) {
          pair<int,int> newp = {d.first + cur.first, d.second + cur.second};
          if(newp.first < 0 || newp.second < 0 || newp.first >= m || newp.second >= n) continue;
          if(mat[newp.first][newp.second] == '1') continue;
          if(nodes[newp.first][newp.second].visited) continue;

          nodes[newp.first][newp.second].visited = true;
          nodes[newp.first][newp.second].dist_sum += dis + 1; // add dist to dist sum
          q.push_back(newp);
          dists.push_back(dis + 1);
        }
      }
    }
    int min_sum = INT_MAX;
    pair<int,int> ret;
    for(int i = 0; i < m; ++i)
      for(int j = 0; j < n; ++j) {
        if(mat[i][j] == '0') {
          if(nodes[i][j].dist_sum < min_sum) {
            min_sum = nodes[i][j].dist_sum;
            ret = {i,j};
          }
        }
      }
    return ret;
  }
}