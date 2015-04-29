/*
一个 n x n 矩阵，每个房间可能是封闭的房间，可能是警察，可能是开的房间，
封闭的房间不能过，返回一个n x n矩阵，每一个元素是最近的警察到这个房间的最短距离。
初始矩阵中-1代表封闭房间，INT_MAX代表普通房间，0代表有警察的房间。

常规思路是对每一个警察做一次BFS，复杂度为O(n^3)。可以一开始找出所有警察，然后一起push到BFS的queue里面，同时搜索。复杂度可降为O(n^2)。
本题出现的频率还是很高的，比如还有这样的描述形式：
给一个matrix里面有人，墙和空格，把空格里填上需要走到最近的人那里的步数。
 */

// bfs level order traversal, set same distance at each level

class Solu{
  void police_and_rooms(vector<vector<int>>& rooms) {
    int n = (int)rooms.size();
    if (n == 0) return;

    set<pair<int,int>> visited;
    queue<pair<int,int>> q;
    queue<int> dists;

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < n; ++j) {
        if (rooms[i][j] == 0) {
          q.push({i,j});
          visited.insert({i,j});
          dists.push(0);
        }
      }
    }
    while(!q.empty()) {
      auto pos = q.front();
      q.pop();

      auto dist = dists.front();
      dists.pop();

      vector<pair<int,int>> dirs =  {{0,1}, {0,-1}, {1,0}, {-1,0}};
      for(auto dir : dirs) {
        pair<int,int> cur = {dir.first + pos.first, dir.second + pos.second};
        if(cur.first < 0 || cur.second < 0 || cur.first >= n || cur.second >= n)
          continue;

        if(visited.count(cur)) continue;
        if(rooms[cur.first][cur.second] == -1) {
          visited.insert(cur);
          continue;
        }

        visited.insert(cur);

        dists.push(dist + 1);
        q.push(cur);

        rooms[cur.first][cur.second] =
            min(rooms[cur.first][cur.second], dist + 1);
      }
    }
  }
}