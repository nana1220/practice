package ch9recursionanddynamicprogramming;

import java.lang.Boolean;
import java.lang.System;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/*
 * Imagine a robot sitting on the upper left comer of an X by Ygrid. The robot can only
 * move in two directions: right and down. How many possible paths are there for the
 * robot to go from (0, 0) to (X, Y) ?
 * FOLLOW UP
 * Imagine certain spots are "off limits," such that the robot cannot step on them.
 * Design an algorithm to find a path for the robot from the top left to the bottom right.
 */
public class FindPath {
  /*
   * C_{x+y}^x
   */
  static int countPaths(int x, int y) {
    int[] cache = new int[x + y + 1];
    int factoXY = factorial(x + y, cache);
    int factoX = factorial(x, cache);
    int factoY = factorial(y, cache);
    return factoXY / (factoX * factoY);
  }

  static int factorial(int x, int[] cache) {
    if (x == 0) {
      cache[0] = 1;
      return 1;
    }
    if (cache[x] == 0) {
      cache[x] = x * factorial(x - 1, cache);
    }
    return cache[x];
  }

  static int factorialIter(int x) {
    int cache = 1;
    for (int n = 1; n < x + 1 ; n++) {
      cache *= n;
    }
    return cache;
  }

  /*
   * backtrack
   * go from (x, y) to (0, 0)
   * push from start of the recursion
   * if go the the deepest resurion and find its a wrong path pop
   */
  static boolean findPath1(int x, int y, int[][] grid, Stack<Point> path) {
    // Early pruning: if current point is off limit, or out of bound
    // don't forget x < 0, y < 0 means hitting the edge trying go out of bound
    // must check bounds first
    if (x < 0 || y < 0 || grid[x][y] == 0) {
      return false;
    }
    // base case
    path.push(new Point(x, y));
    if (x == 0 && y == 0) {
      return true;
    }
    if (!findPath1(x - 1, y, grid, path) && !findPath1(x, y - 1, grid, path)) {
      path.pop();
      return false;
    }
    return true;
  }

  /*
   * backtrack
   * use queue, reach the deepest recursion find the path then add from the deepest recursion
   * go from (x, y) to (0, 0)
   */
  static boolean findPath2(int x, int y, int[][] grid, LinkedList<Point> path) {
    // Pruning case: if current point is off limit, or out of bound
    // must check bounds first
    if (x < 0 || y < 0 || grid[x][y] == 0) {
      return false;
    }
    if ((x == 0 && y == 0) || findPath2(x - 1, y, grid, path) || findPath2(x, y - 1, grid, path)) {
      path.add(new Point(x, y));
      return true;
    }
    return false;
  }

  /*
   * backtrack
   * go from (0, 0) to (X, Y)
   * use stack, find the right path then push from deepest recursion
   */
  static boolean findPath3(int x, int y, int[][] grid, Stack<Point> path, int X, int Y) {
    // Pruning case: if current point is off limit, or out of bound
    // must check bounds first
    if (x > X || y > Y || grid[x][y] == 0) {
      return false;
    }
    if ((x == X && y == Y) || findPath3(x + 1, y, grid, path, X, Y) || findPath3(x, y + 1, grid, path, X, Y)) {
      path.push(new Point(x, y));
      return true;
    }
    return false;
  }

  /*
   * DP: mark the point that already being visited
   */
  static boolean findPathDP1(Point currPoint, int[][] grid, LinkedList<Point> path, HashSet<Point> visited) {
    // Pruning case: if current point is out of bound
    // must check bounds first
    if (currPoint.x < 0 || currPoint.y < 0) {
      return false;
    }
    // DP case: already visited means this point doesn't lead to target point
    if (visited.contains(currPoint)) {
      return false;
    } else {
      visited.add(currPoint); // mark visited
    }
    // Pruning case: if current point is off limit
    if (grid[currPoint.x][currPoint.y] == 0) {
      return false;
    }
    if ((currPoint.x == 0 && currPoint.y == 0)
        || findPathDP1(new Point(currPoint.x - 1, currPoint.y), grid, path, visited)
        || findPathDP1(new Point(currPoint.x, currPoint.y - 1), grid, path, visited)) {
      path.add(currPoint);
      return true;
    }
    return false;
  }

  /*
   * DP2: cache the result instead of just a mark
   */
  static boolean findPathDP2(int x, int y, int[][] grid, LinkedList<Point> path, HashMap<Point, Boolean> cache) {
    // early pruing case: if out of bounds or not available
    if (y < 0 || x < 0 || grid[x][y] == 0) {
      return false;
    }
    Point p = new Point(x, y); // Note: key of hashmap is compared by value not reference
    // DP case: If already visited return cache value
    if (cache.containsKey(p)) {
      return cache.get(p);
    }
    boolean success = false;
	// if there's a path from the start to current location
    if (((x == 0) && (y == 0)) || findPathDP2(x, y - 1, grid, path, cache) || findPathDP2(x - 1, y, grid, path, cache)) {
      path.add(p);
      success = true;
    }
    cache.put(p, success); // Cache result
    return success;
  }

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) {
    // count paths
    System.out.println(countPaths(5, 5));
    int[] cache = new int[7];
    System.out.println(factorial(5, cache));
    System.out.println(factorialIter(10));

    // find path
    int[][] grid1 = new int[][] {
        { 1, 1, 0, 1, 0 },
        { 1, 1, 1, 0, 1 },
        { 1, 0, 1, 1, 1 },
        { 1, 1, 1, 1, 1 }
    };
    int[][] grid2 = new int[][] {
        { 1, 1, 0, 1, 0 },
        { 0, 0, 1, 0, 1 },
        { 0, 1, 1, 1, 1 },
        { 1, 1, 0, 1, 1 }
    };
    Stack<Point> path1 = new Stack<Point>();
    LinkedList<Point> path2 = new LinkedList<Point>();
    Stack<Point> path3 = new Stack<Point>();
    LinkedList<Point> path2DP = new LinkedList<Point>();
    System.out.println(findPath1(3, 4, grid2, path1));
    System.out.println(findPath2(3, 4, grid2, path2));
    System.out.println(findPath3(0, 0, grid2, path3, 3, 4));

    path1.clear();
    System.out.println(findPath1(3, 4, grid1, path1));
    while (!path1.isEmpty()) {
      Point p = path1.pop();
      System.out.println("(" + p.x + ", " + p.y + ")");
    }
    path2.clear();
    System.out.println(findPath2(3, 4, grid1, path2));
    for (Point p : path2)
      System.out.println("(" + p.x + ", " + p.y + ")");
    path3.clear();
    System.out.println(findPath3(0, 0, grid1, path3, 3, 4));
    while (!path3.isEmpty()) {
      Point p = path3.pop();
      System.out.println("(" + p.x + ", " + p.y + ")");
    }
    System.out.println(findPathDP1(new Point(3, 4), grid1, path2DP, new HashSet<Point>()));
    for (Point p : path2DP)
      System.out.println("(" + p.x + ", " + p.y + ")");
    path2DP.clear();
    System.out.println(findPathDP2(3, 4, grid1, path2DP, new HashMap<Point, Boolean>()));
    for (Point p : path2DP)
      System.out.println("(" + p.x + ", " + p.y + ")");
  }
}