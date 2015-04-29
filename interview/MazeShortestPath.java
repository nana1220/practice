/*
Find a shortest path in a N*N matrix maze from (0,0) to (N,N), assume 1 is passable, 0 is not, 3 is destination, use
memorization to cache the result.
 */

class Solu{
  using pt = pair<int,int>;
  void path(const vector<vector<int>>& maze, pt start, pt end) {
    if (maze.empty() || maze[0].empty()) return;

    vector<pt> path;
    const vector<pt> dirs = {{0,1},{1,0},{0,-1},{-1,0}};

    queue<pt> q;
    q.push(start);

    set<pt> visited;
    visited.insert(start);

    map<pt,pt> pred;
    auto test_pt = [&](const pt& p) {
      if (p.first < 0 || p.second < 0 ||
          p.first >= maze.size() || p.second >= maze[0].size()) return false;
      if (maze[p.first][p.second] == 1) return false;
      return true;
    };

    while(!q.empty()) {
      auto cur = q.front();
      q.pop();
      for(auto& dir : dirs) {
        pt new_pt{dir.first + cur.first, dir.second + cur.second};
        if (!test_pt(new_pt)) continue;

        if (!visited.count(new_pt)) {
          q.push(new_pt);
          visited.insert(new_pt);
          pred[new_pt] = cur;

          if (new_pt == end) {
            q = queue<pt>();
            auto cur_pred = pred[end];
            while(cur_pred != start) {
              path.push_back(cur_pred);
              cur_pred = pred[cur_pred];
            }
            break;
          }
        }
      }
    }

    path.push_back(start);
    reverse(path.begin(), path.end());
    for(auto& p : path) {
      cout << "(" << p.first << "," << p.second << ")" << " ";
    }
    cout << endl;
  }

}

class Solu{
  public static class MazeResult {
    public boolean solved;
    public List<String> res = new ArrayList<String>();
    public int steps = Integer.MAX_VALUE;
    public MazeResult(boolean isSolved) {
      solved = isSolved;
    }
  }

  static Map<String, MazeResult> cache = new HashMap<String, MazeResult>();
  static MazeResult solveMaze(int[][] m, int r, int c, List<String> path, HashSet<String> visited) {
    if (r < 0 || r >= m.length || c < 0 || c >= m[0].length)
      return new MazeResult(false);

    String cell = r + "" + c + "";
    if (m[r][c]==0 || visited.contains(cell))
      return new MazeResult(false);
    if (m[r][c] == 3) {
      MazeResult ret = new MazeResult(true);
      ret.res = new ArrayList<String>(path);
      ret.res.add(cell);
      ret.steps = path.size() + 1;
      return ret;
    }
    if (cache.containsKey(cell))
      return cache.get(cell);

    path.add(cell);
    visited.add(cell);
    MazeResult ret = new MazeResult(false), temp = new MazeResult(false);

    temp = solveMaze(m, r, c+1, path, visited);
    compareResult(temp, ret);

    temp = solveMaze(m, r, c-1, path, visited);
    compareResult(temp, ret);

    temp = solveMaze(m, r+1, c, path, visited);
    compareResult(temp, ret);

    temp = solveMaze(m, r-1, c, path, visited);
    compareResult(temp, ret);

    path.remove(path.size()-1);
    visited.remove(cell);

    cache.put(cell, ret);
    return ret;
  }

  private static void compareResult(MazeResult temp, MazeResult ret) {
    if (temp.solved) {
      if (temp.steps < ret.steps) {
        ret.res = temp.res;
        ret.steps = temp.steps;
      }
      ret.solved = true;
    }
  }

}