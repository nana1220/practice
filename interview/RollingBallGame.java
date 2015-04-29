/*
一个球从起点开始沿着通道，看能不能滚到终点。不过有限制， 每次球一走到底要不到边界，要不到障碍物，中间不能停留。 可以上下左右走，
然后让写个function 给定起点， 终点，和图，判断是不是solvable.
For example (1代表有障碍, 0代表可以通过):
⎡⎣⎢begin1100001011end⎤⎦⎥
return false



⎡⎣⎢begin1100000011end⎤⎦⎥
return true
 */

// bfs

class Solu{
  bool solvable(const vector<string>& board, pair<int,int> start, pair<int,int> end) {
    if(board.empty() || board[0].empty()) return false;
    int m = (int)board.size(), n = (int)board[0].size();

    deque<pair<int,int>> q = {start};
    vector<vector<bool>> visited(m, vector<bool>(n, false));
    visited[start.first][start.second] = true;

    auto get_next = [&](int row, int col) {
      vector<pair<int,int>> next;
      bool found = false;
      for(int i = row + 1; i <= m; ++i)
        if(board[i][col] == '1') {
          next.push_back({i - 1, col});
          found = true;
          break;
        }
      if(!found && row != m - 1) next.emplace_back(m-1, col);
      found = false;

      for(int i = row - 1; i >= 0; --i)
        if(board[i][col] == '1') {
          next.push_back({i + 1, col});
          found = true;
          break;
        }
      if(!found && row != 0) next.emplace_back(0, col);
      found = false;

      for(int i = col + 1; i < n; ++i)
        if(board[row][i] == '1') {
          next.push_back({row, i - 1});
          found = true;
          break;
        }
      if(!found && col != n - 1) next.emplace_back(row, n-1);
      found = false;

      for(int i = col - 1; i >= 0; --i)
        if(board[row][i] == '1') {
          next.push_back({row, i + 1});
          found = true;
          break;
        }
      if(!found && col != 0) next.emplace_back(row, 0);
      return next;
    };

    while(!q.empty()) {
      auto cur = q.front();
      q.pop_front();

      auto next = get_next(cur.first, cur.second);
      for(auto n : next) {
        if(visited[n.first][n.second]) continue;
        visited[n.first][n.second] = true;
        if(n == end) return true;
        q.push_back(n);
      }
    }
    return false;
  }
}