/*
There is a museum organized as NxN room. Some rooms are   locked and inaccessible. Other rooms are
open and some rooms have guards. Guards can only move north, south, east and west, only through open
 rooms and only within the museum. For each room, find the shortest distance to a guard. What is
 the time complexity of your algorithm
 */

class Room {
  int x;
  int y;
  boolean hasGuard;
  boolean isOpen;
  int dist;
}
class Museum {

  // BFS: time O(n^2)
  void shortDist(Room[][] museum) {
    LinkedList<Room> queue = new LinkedList<Room>();
    for (int i = 0; i < N; i++) {
      for (int j =0; j < N; j++) {
        if (museum[i][j].hasGuard) {
          museum[i][j].dist = 0;    // 0 means room with guard
          queue.add(museum[i][j]);
        } else (museum[i][j].dist = -1); // -1 means distance not computed yet, serve visited marker
      }
    }
    while (!queue.isEmpty()) {
      Room room = queue.poll();
      int x = room.x;
      int y = room.y;
      if (x + 1 < N && museum[x + 1][y].isOpen && museum[x +1][y].dist < 1) {
        museum[x+1][y].dist = room.dist + 1;
        queue.add(museum[x+1][y]);
      }
      if (x - 1 >= 0 && museum[x - 1][y].isOpen && museum[x -1][y].dist < 1) {
        museum[x-1][y].dist = room.dist + 1;
        queue.add(museum[x-1][y]);
      }
      if (y + 1 < N && museum[x][y + 1].isOpen && museum[x][y + 1].dist < 1) {
        museum[x][y + 1].dist = room.dist + 1;
        queue.add(museum[x][y + 1]);
      }
      if (y - 1 < N && museum[x][y- 1].isOpen && museum[x][y- 1].dist < 1) {
        museum[x][y- 1].dist = room.dist + 1;
        queue.add(museum[x][y- 1]);
      }
    }
  }
}
