/*
输入是一个 N*N的矩阵，代表地势高度。如果下雨水流只能流去比他矮或者一样高的地势。
矩阵左边和上边是太平洋，右边和下边是大西洋。求出所有的能同时流到两个大洋的点。
 */

/*
Brutal force的解法很简单，每个点搜索一次，接触到太平洋或者大西洋就记录下来。最后扫一遍输出同时能走到两个ocean的点即可。
复杂度为O(n^2)。
优化的话，可以从两个ocean的点开始，从外往里搜。首先搜所有太平洋的点，记录下能接触到的position。然后同理搜大西洋的点，求出交集即可。
搜索用DFS和BFS都可以，复杂度为O(n^2)。
 */

// each point at most visited twice, O(n^2)
public class Solution {
  void search(Point pt, HashMap<Point,Boolean> visited, int[][] mat) {
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    for(int i = 0; i < 4; ++i) {
      int[] dir = dirs[i];
      Point new_pt = new Point(dir[0] + pt.x, dir[1] + pt.y);
      if( new_pt.x < 0 || new_pt.x >= mat.length || new_pt.y < 0 || new_pt.y >= mat.length ) {
        continue;
      }
      if( mat[new_pt.x][new_pt.y] < mat[pt.x][pt.y] || visited.containsKey(new_pt) ) {
        continue;
      }
      visited.put(new_pt, true);
      search(new_pt, visited, mat);
    }
  }

  public List<Point> flowing_water(int[][] mat) {
    int n = mat.length;

    HashMap<Point, Boolean> visited_pac = new HashMap<Point, Boolean>();
    for(int i = 0; i < n; ++i) {
      Point p = new Point(0,i);
      visited_pac.put(p, true);
      search(p, visited_pac, mat);
    }
    for(int i = 0; i < n; ++i) {
      Point p = new Point(i,0);
      visited_pac.put(p, true);
      search(p, visited_pac, mat);
    }

    HashMap<Point, Boolean> visited_alt = new HashMap<Point, Boolean>();
    for(int i = 0; i < n; ++i) {
      Point p = new Point(n-1,i);
      visited_alt.put(p, true);
      search(p, visited_alt, mat);
    }

    for(int i = 0; i < n; ++i) {
      Point p = new Point(i,n-1);
      visited_alt.put(p, true);
      search(p, visited_alt, mat);
    }
    ArrayList<Point> ret = new ArrayList<Point>();
    for(Point key : visited_alt.keySet()) {
      if(visited_pac.containsKey(key)) {
        ret.add(key);
      }
    }
    return ret;
  }
}